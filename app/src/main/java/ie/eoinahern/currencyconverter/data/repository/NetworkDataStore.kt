package ie.eoinahern.currencyconverter.data.repository

import ie.eoinahern.currencyconverter.data.network.MyApi


class NetworkDataStore constructor(private val api: MyApi) : CurrencyDataStore {

}