package ie.eoinahern.currencyconverter.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.eoinahern.currencyconverter.domain.BaseUsecase
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.domain.usecase.GetCurrency
import ie.eoinahern.currencyconverter.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import javax.inject.Inject

class CurrencyConverterViewModel @Inject constructor(private val getCurrencies: GetCurrency) : BaseViewModel() {

    private val currencyData: MutableLiveData<Pair<DomainCurrency, List<DomainCurrency>>> = MutableLiveData()
    private val job = Job()
    private val dispatcher = Dispatchers.Main
    private val scope = CoroutineScope(job + dispatcher)

    fun getCurrencyList() = getCurrencies(BaseUsecase.None(), scope) { it.either(::onError, ::updateList) }

    fun observeData(): LiveData<Pair<DomainCurrency, List<DomainCurrency>>> = currencyData

    private fun onError(failure: Failure) {
        failureLiveData.postValue(failure)
    }

    private fun updateList(list: Pair<DomainCurrency, List<DomainCurrency>>) {
        currencyData.postValue(list)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}