package ie.eoinahern.currencyconverter.data.repository.graph

import ie.eoinahern.currencyconverter.data.network.MyApi
import ie.eoinahern.currencyconverter.tools.DateUtil
import javax.inject.Inject


class GraphDataStorFactory @Inject constructor(myApi: MyApi, dateUtil: DateUtil) {

    private val dataSource: GraphDataSource

    init {
        dataSource = GraphNetworkDataSource(myApi, dateUtil)
    }

    fun getDataStore(): GraphDataSource = dataSource
}