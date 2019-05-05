package ie.eoinahern.currencyconverter.di.module

import android.app.Application
import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import ie.eoinahern.currencyconverter.data.network.MyApi
import okhttp3.HttpUrl
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
    fun getKey(): String = "50a76bbf05feab69b722f45e51e71dee"

    @Provides
    @Singleton
    fun getAPiEndpoint() = HttpUrl.parse("http://data.fixer.io/api/")

    @Provides
    @Singleton
    fun getMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun getApi(key: String, url: HttpUrl, moshi: Moshi): MyApi {

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(MyApi::class.java)
    }
}