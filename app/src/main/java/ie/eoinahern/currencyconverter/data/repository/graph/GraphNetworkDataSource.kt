package ie.eoinahern.currencyconverter.data.repository.graph

import ie.eoinahern.currencyconverter.data.model.mapper.GraphDataMapper
import ie.eoinahern.currencyconverter.data.network.MyApi


class GraphNetworkDataSource(private val myApi: MyApi) : GraphDataSource {

    override fun getList(start: String, end: String, symbol: String): Map<String, Double> {
        return GraphDataMapper.mapData(
            requireNotNull(
                myApi.getTimeSeries(start, end, listOf(symbol))
                    .execute().body()
            )
        )
    }
}