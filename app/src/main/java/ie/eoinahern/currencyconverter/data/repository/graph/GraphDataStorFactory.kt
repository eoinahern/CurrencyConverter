package ie.eoinahern.currencyconverter.data.repository.graph

import ie.eoinahern.currencyconverter.data.network.MyApi
import javax.inject.Inject


class GraphDataStorFactory @Inject constructor(myApi: MyApi) {

    private val dataSource: GraphDataSource

    init {
        dataSource = GraphNetworkDataSource(myApi)
    }

    fun getDataStore(): GraphDataSource = dataSource
}