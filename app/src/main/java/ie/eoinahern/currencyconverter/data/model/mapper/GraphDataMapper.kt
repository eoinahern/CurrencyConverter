package ie.eoinahern.currencyconverter.data.model.mapper

import ie.eoinahern.currencyconverter.data.model.GraphData
import org.threeten.bp.LocalDate


object GraphDataMapper {

    fun mapData(graphData: GraphData, dateMap: Map<String, String>): Map<String, Map<String, Double>> {

        val ratesForRange = graphData.rates

        return getAllRequiredRanges(ratesForRange.map {
            it.key to it.value.entries.first().value
        }.toMap(), dateMap)
    }

    private fun getAllRequiredRanges(
        sixmonthPriceRange: Map<String, Double>,
        mapOfRequiredRangesMap: Map<String, String>
    ): Map<String, Map<String, Double>> {


        return mapOfRequiredRangesMap.map {
            val (requiredDateKey, startDate) = it
            requiredDateKey to sixmonthPriceRange.filter { dateAndAmountEntry ->
                !LocalDate.parse(dateAndAmountEntry.key)
                    .isBefore(LocalDate.parse(startDate))
            }
        }.toMap()
    }
}