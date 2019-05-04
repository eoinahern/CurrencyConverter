package ie.eoinahern.currencyconverter.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ie.eoinahern.currencyconverter.di.annotation.MyMapKey
import ie.eoinahern.currencyconverter.ui.details.CurrencyConverterViewModel
import ie.eoinahern.currencyconverter.ui.selection.GraphActivtyViewModel


@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @MyMapKey(CurrencyConverterViewModel::class)
    abstract fun getCurrencyViewModel(viewModel: CurrencyConverterViewModel): ViewModel

    @Binds
    @IntoMap
    @MyMapKey(GraphActivtyViewModel::class)
    abstract fun getSelectionViewModel(otherViewModel: GraphActivtyViewModel): ViewModel

}