package ie.eoinahern.currencyconverter.data.repository.graph

import ie.eoinahern.currencyconverter.data.network.MyApi
import ie.eoinahern.currencyconverter.tools.DateUtil
import javax.inject.Inject


class GraphDataStorFactory @Inject constructor(myApi: MyApi, dateUtil: DateUtil) {

    /**
     * could also implement cachingData source here.
     * example in other repository.
     */

    private val dataSource: GraphDataSource

    init {
        dataSource = GraphNetworkDataSource(myApi, dateUtil)
    }

    fun getDataStore(): GraphDataSource = dataSource
}