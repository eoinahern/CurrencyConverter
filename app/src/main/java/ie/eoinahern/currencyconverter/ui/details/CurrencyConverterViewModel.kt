package ie.eoinahern.currencyconverter.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.eoinahern.currencyconverter.domain.BaseUsecase
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.domain.usecase.GetCurrency
import ie.eoinahern.currencyconverter.ui.base.BaseViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class CurrencyConverterViewModel @Inject constructor(
    private val getCurrencies: GetCurrency,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val currencyData: MutableLiveData<Pair<DomainCurrency, List<DomainCurrency>>> = MutableLiveData()
    private val job = Job()
    private val scope = CoroutineScope(job + dispatcher)

    fun getCurrencyList() {
        getCurrencies(BaseUsecase.None(), scope) { it.either(::onError, ::updateList) }
    }

    fun observeData(): LiveData<Pair<DomainCurrency, List<DomainCurrency>>> = currencyData

    private fun onError(failure: Failure) {
        failureLiveData.value = failure
    }

    private fun updateList(list: Pair<DomainCurrency, List<DomainCurrency>>) {
        currencyData.value = list
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}