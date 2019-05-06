package ie.eoinahern.currencyconverter.data.network

import ie.eoinahern.currencyconverter.data.model.CurrencySymbols
import ie.eoinahern.currencyconverter.data.model.LatestCurrencies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("latest")
    fun getData(): Call<LatestCurrencies>

    @GET("timeseries")
    fun getTimeSeries(
        @Query("start_date") start: String, @Query("end_date") end: String,
        @Query("symbols") symbols: List<String>
    )

    @GET("symbols")
    fun getAllSymbls(): Call<CurrencySymbols>
}