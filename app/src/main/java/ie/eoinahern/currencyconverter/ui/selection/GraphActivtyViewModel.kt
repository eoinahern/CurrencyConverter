package ie.eoinahern.currencyconverter.ui.selection


import com.github.mikephil.charting.data.Entry
import ie.eoinahern.currencyconverter.data.model.GraphNestedMap
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.usecase.GetGraphData
import ie.eoinahern.currencyconverter.tools.TWOFOUR_HOUR_KEY
import ie.eoinahern.currencyconverter.ui.base.BaseViewModel
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject

class GraphActivtyViewModel @Inject constructor(
    private val getGraph: GetGraphData, dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    /**
     * had to use publishSubject as workaround here
     * for updating the graphData.
     */

    private val graphData: PublishSubject<List<Entry>> = PublishSubject.create()

    private val job = Job()
    private val scope = CoroutineScope(job + dispatcher)

    private lateinit var allGraphedData: GraphNestedMap

    fun getGraphData(symbol: String) {
        getGraph(symbol, scope) { it.either(::onError, ::onResult) }
    }

    fun getGraphData(): PublishSubject<List<Entry>> = graphData

    fun onResult(map: GraphNestedMap) {
        allGraphedData = map
        graphData.onNext(requireNotNull(allGraphedData[TWOFOUR_HOUR_KEY]))
    }

    fun updateGraphData(key: String) {

        if (::allGraphedData.isInitialized)
            graphData.onNext(requireNotNull(allGraphedData[key]))
    }

    fun onError(failure: Failure) {
        failureLiveData.postValue(failure)
    }

}