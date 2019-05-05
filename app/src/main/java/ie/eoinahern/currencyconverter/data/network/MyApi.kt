package ie.eoinahern.currencyconverter.data.network

import retrofit2.http.GET

interface MyApi {

    @GET("latest")
    fun getData()
}