package ie.eoinahern.currencyconverter.data.model.mapper

import ie.eoinahern.currencyconverter.data.model.GraphData


object GraphDataMapper {

    fun mapData(graphData: GraphData): Map<String, Double> {
        val rates = graphData.rates

        return rates.map {
            it.key to it.value.entries.first().value
        }.toMap()
    }
}