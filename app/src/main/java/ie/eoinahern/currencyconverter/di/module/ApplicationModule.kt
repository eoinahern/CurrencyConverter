package ie.eoinahern.currencyconverter.di.module

import android.app.Application
import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import ie.eoinahern.currencyconverter.data.network.MyApi
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
    fun getContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun getAPiEndpoint() = HttpUrl.parse(FIXER_URL)

    @Provides
    @Singleton
    fun getMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

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
}