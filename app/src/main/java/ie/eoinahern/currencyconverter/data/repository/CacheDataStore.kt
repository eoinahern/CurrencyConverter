package ie.eoinahern.currencyconverter.data.repository

import ie.eoinahern.currencyconverter.data.database.CurrencyDAO


class CacheDataStore constructor(private val currencyDAO: CurrencyDAO) : CurrencyDataStore {

}