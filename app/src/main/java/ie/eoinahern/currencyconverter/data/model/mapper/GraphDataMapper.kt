package ie.eoinahern.currencyconverter.data.model.mapper

import com.github.mikephil.charting.data.Entry
import ie.eoinahern.currencyconverter.data.model.GraphData
import ie.eoinahern.currencyconverter.data.model.GraphNestedMap
import org.threeten.bp.LocalDate


object GraphDataMapper {

    fun mapData(graphData: GraphData, dateMap: Map<String, String>): GraphNestedMap {
        val ratesForRange = graphData.rates
        return getAllRequiredRanges(ratesForRange.map {
            it.key to it.value.entries.first().value
        }.toMap(), dateMap)
    }

    /**
     * returns a map (typeAlias)
     * with a string as key (represents date range)
     * and a value (represents price range)
     * with these i create and Entry and add them to a list
     *
     */

    private fun getAllRequiredRanges(
        sixmonthPriceRange: Map<String, Double>,
        mapOfRequiredRangesMap: Map<String, String>
    ): GraphNestedMap {

        return mapOfRequiredRangesMap.map {
            val (requiredDateKey, startDate) = it
            requiredDateKey to sixmonthPriceRange.filter { dateAndAmountEntry ->
                !LocalDate.parse(dateAndAmountEntry.key)
                    .isBefore(LocalDate.parse(startDate))
            }.map { priceRange ->
                val (date, price) = priceRange
                Entry(
                    LocalDate.parse(date).toEpochDay().toFloat()
                    , price.toFloat()
                )
            }
        }.toMap()
    }
}