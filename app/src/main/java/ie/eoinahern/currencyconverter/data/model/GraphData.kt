package ie.eoinahern.currencyconverter.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class GraphData(
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "rates")
    val rates: Map<String, Map<String, Double>>
)