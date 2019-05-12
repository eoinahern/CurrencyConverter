package ie.eoinahern.currencyconverter.ui.selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ie.eoinahern.currencyconverter.data.model.GraphNestedMap
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.usecase.GetGraphData
import ie.eoinahern.currencyconverter.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject

class GraphActivtyViewModel @Inject constructor(private val getGraph: GetGraphData) : BaseViewModel() {

    private val graphData: MutableLiveData<GraphNestedMap> = MutableLiveData()

    private val job = Job()
    private val dispatcher = Dispatchers.Main
    private val scope = CoroutineScope(job + dispatcher)

    fun getGraphData(symbol: String) {
        getGraph(symbol, scope) { it.either(::onError, ::onResult) }
    }

    fun liveGrapData(): LiveData<GraphNestedMap> = graphData

    fun onResult(map: GraphNestedMap) {
        graphData.postValue(map)
    }

    fun onError(failure: Failure) {
        failureLiveData.postValue(failure)
    }

}