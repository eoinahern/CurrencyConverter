package ie.eoinahern.currencyconverter.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ie.eoinahern.currencyconverter.ui.details.CurrencyConverterViewModel
import ie.eoinahern.currencyconverter.ui.selection.GraphActivtyViewModel


@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    abstract fun getCurrencyViewModel(): CurrencyConverterViewModel

    @Binds
    @IntoMap
    abstract fun getSelectionViewModel(): GraphActivtyViewModel

}