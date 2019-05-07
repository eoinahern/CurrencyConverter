package ie.eoinahern.currencyconverter.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ie.eoinahern.currencyconverter.domain.exception.Failure


open class BaseViewModel : ViewModel() {

    private val failureLiveData = MutableLiveData<Failure>()

    fun getFailureResult(): LiveData<Failure> {
        return failureLiveData
    }
}