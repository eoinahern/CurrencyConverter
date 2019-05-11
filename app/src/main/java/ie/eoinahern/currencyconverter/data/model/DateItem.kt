package ie.eoinahern.currencyconverter.data.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class DateItem(
    val date: Map<String, Map<String, Double>>
)