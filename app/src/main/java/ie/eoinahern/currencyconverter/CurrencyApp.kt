package ie.eoinahern.currencyconverter

import android.app.Application
import ie.eoinahern.currencyconverter.di.ApplicationComponent

class CurrencyApp : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
    }


    fun getAppCOmponent(): ApplicationComponent = appComponent
}