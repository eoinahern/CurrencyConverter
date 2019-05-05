package ie.eoinahern.currencyconverter.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LatestCurrencies(
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "timestamp")
    val timestamp: Long,
    @Json(name = "base")
    val base: String,
    @Json(name = "rates")
    val rates: Map<String, Double>
)