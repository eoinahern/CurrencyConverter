package ie.eoinahern.currencyconverter.di

import android.app.Application
import android.content.Context
import dagger.Provides
import javax.inject.Singleton

class ApplicationModule(val app: Application) {

    @Provides
    @Singleton
    fun getContext(): Context = app

    @Provides
    @Singleton
    fun getKey(): String = "123455678"

}