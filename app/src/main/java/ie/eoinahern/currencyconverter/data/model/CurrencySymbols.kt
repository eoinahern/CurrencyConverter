package ie.eoinahern.currencyconverter.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CurrencySymbols(
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "symbols")
    val symbols: Map<String, String>
)