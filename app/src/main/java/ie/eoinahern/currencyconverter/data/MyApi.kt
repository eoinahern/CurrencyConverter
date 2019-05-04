package ie.eoinahern.currencyconverter.data

import retrofit2.http.GET

interface MyApi {

    @GET("latest")
    fun getData()
}