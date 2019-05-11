package ie.eoinahern.currencyconverter.data.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GraphData(
    val success: Boolean,
    val start_date: String,
    val end_date: String,
    val rates: DateItem
)