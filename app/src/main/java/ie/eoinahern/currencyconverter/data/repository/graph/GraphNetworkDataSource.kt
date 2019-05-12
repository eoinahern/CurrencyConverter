package ie.eoinahern.currencyconverter.data.repository.graph

import ie.eoinahern.currencyconverter.data.model.mapper.GraphDataMapper
import ie.eoinahern.currencyconverter.data.network.MyApi
import ie.eoinahern.currencyconverter.tools.DateUtil
import ie.eoinahern.currencyconverter.tools.NOW_KEY
import ie.eoinahern.currencyconverter.tools.SIX_MONTH_KEY


class GraphNetworkDataSource(
    private val myApi: MyApi,
    private val dateUtil: DateUtil
) : GraphDataSource {


    /**
     * need to make multiple api calls here.
     * or reuse the largest map and sub divide?
     * this would mean less calls to backend so we can do this
     */

    override fun getList(symbol: String): Map<String, Map<String, Double>> {

        val datesMap = dateUtil.getLatestDateRanget()
        val result = myApi.getTimeSeries(
            datesMap[SIX_MONTH_KEY] ?: "",
            datesMap[NOW_KEY] ?: "", listOf(symbol)
        ).execute()
        val body = result.body()

        return GraphDataMapper.mapData(requireNotNull(body), datesMap)

    }
}