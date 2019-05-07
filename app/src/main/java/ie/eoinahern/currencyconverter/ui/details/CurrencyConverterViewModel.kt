package ie.eoinahern.currencyconverter.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.eoinahern.currencyconverter.domain.BaseUsecase
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.domain.usecase.GetCurrency
import ie.eoinahern.currencyconverter.ui.base.BaseViewModel
import javax.inject.Inject

class CurrencyConverterViewModel @Inject constructor(private val getCurrencies: GetCurrency) : BaseViewModel() {

    private val currencyData: LiveData<List<DomainCurrency>> = MutableLiveData()

    fun getCurrencyList() = getCurrencies(BaseUsecase.None()) { it.either(::onError, ::updateList) }

    fun observeData(): LiveData<List<DomainCurrency>> {
        return currencyData
    }


    private fun onError(fail : Failure) {
        //do something with failure
    }


    fun updateList(list : List<DomainCurrency>) {
        println(list)
    }


}