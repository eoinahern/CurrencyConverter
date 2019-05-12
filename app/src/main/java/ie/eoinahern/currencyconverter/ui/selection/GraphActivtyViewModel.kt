package ie.eoinahern.currencyconverter.ui.selection

import androidx.lifecycle.MutableLiveData
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.usecase.GetGraphData
import ie.eoinahern.currencyconverter.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class GraphActivtyViewModel @Inject constructor(private val getGraph: GetGraphData) : BaseViewModel() {

    private val graphData: MutableLiveData<Map<String, Map<String, Double>>> = MutableLiveData()

    private val job = Job()
    private val dispatcher = Dispatchers.Main
    private val scope = CoroutineScope(job + dispatcher)

    fun getGraphData() {
        getGraph("EUR", scope) { it.either(::onError, ::onResult) }
    }

    fun onResult(map: Map<String, Map<String, Double>>) {
        graphData.postValue(map)
    }

    fun onError(failure: Failure) {
        failureLiveData.postValue(failure)
    }

}