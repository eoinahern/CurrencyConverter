package ie.eoinahern.currencyconverter.di.component

import dagger.BindsInstance
import dagger.Component
import ie.eoinahern.currencyconverter.CurrencyApp
import ie.eoinahern.currencyconverter.di.module.ApplicationModule
import javax.inject.Singleton
import dagger.android.support.AndroidSupportInjectionModule
import ie.eoinahern.currencyconverter.di.module.ActivityBuilder
import ie.eoinahern.currencyconverter.di.module.ViewModelsModule


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ViewModelsModule::class, ApplicationModule::class, ActivityBuilder::class]
)
interface ApplicationComponent {

    fun inject(app: CurrencyApp)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
        @BindsInstance
        fun application(application: CurrencyApp): Builder
    }

}