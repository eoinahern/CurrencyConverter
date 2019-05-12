package ie.eoinahern.currencyconverter.data.model

import com.github.mikephil.charting.data.Entry
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

typealias GraphNestedMap = Map<String, List<Entry>>

@JsonClass(generateAdapter = true)
data class GraphData(
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "rates")
    val rates: Map<String, Map<String, Double>>
)