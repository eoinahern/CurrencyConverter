package ie.eoinahern.currencyconverter.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import ie.eoinahern.currencyconverter.CurrencyApp
import ie.eoinahern.currencyconverter.data.database.CurrencyDAO
import ie.eoinahern.currencyconverter.data.database.CurrencyDatabase
import ie.eoinahern.currencyconverter.data.network.MyApi
import ie.eoinahern.currencyconverter.tools.DATABASE_NAME
import ie.eoinahern.currencyconverter.tools.FIXER_KEY
import ie.eoinahern.currencyconverter.tools.FIXER_KEY_NAME
import ie.eoinahern.currencyconverter.tools.FIXER_URL
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun getContext(application: CurrencyApp): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun getAPiEndpoint() = HttpUrl.parse(FIXER_URL)

    @Provides
    @Singleton
    fun getMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


    @Provides
    @Singleton
    fun getSharedPrefs(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun getEditor(prefs: SharedPreferences): SharedPreferences.Editor {
        return prefs.edit()
    }

    @Singleton
    @Provides
    fun getClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val initialRequest = chain.request()
            val url = chain.request().url().newBuilder().addQueryParameter(FIXER_KEY_NAME, FIXER_KEY).build()
            val newRequest = initialRequest.newBuilder().url(url).build()
            chain.proceed(newRequest)
        }

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun getApi(url: HttpUrl?, moshi: Moshi, client: OkHttpClient): MyApi {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build().create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun getDatabase(context: Context): CurrencyDAO {
        return Room.databaseBuilder(context, CurrencyDatabase::class.java, DATABASE_NAME)
            .build().currencyDAO()
    }
}