package ie.eoinahern.currencyconverter.data.repository

import ie.eoinahern.currencyconverter.data.database.CurrencyDAO
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency


class CacheDataStore constructor(private val currencyDAO: CurrencyDAO) : CurrencyDataStore {

    override
    fun getCurrencyList(): List<DomainCurrency> {
        return currencyDAO.getAll()
    }

}