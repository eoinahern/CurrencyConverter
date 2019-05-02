package ie.eoinahern.currencyconverter

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import ie.eoinahern.currencyconverter.di.ApplicationComponent
import ie.eoinahern.currencyconverter.di.ApplicationModule

class CurrencyApp : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this))
            .build()
    }

    fun getAppCOmponent(): ApplicationComponent = appComponent
}