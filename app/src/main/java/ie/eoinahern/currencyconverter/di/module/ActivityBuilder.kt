package ie.eoinahern.currencyconverter.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ie.eoinahern.currencyconverter.ui.details.CurrencyConverterActivity
import ie.eoinahern.currencyconverter.ui.selection.GraphActivity


@Module abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindDetails(): CurrencyConverterActivity

    @ContributesAndroidInjector
    abstract fun selectionDetails(): GraphActivity

}