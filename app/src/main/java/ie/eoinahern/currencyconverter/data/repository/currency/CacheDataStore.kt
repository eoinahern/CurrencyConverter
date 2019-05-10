package ie.eoinahern.currencyconverter.data.repository.currency

import ie.eoinahern.currencyconverter.data.database.CurrencyDAO
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.tools.Either


class CacheDataStore constructor(private val currencyDAO: CurrencyDAO) :
    CurrencyDataStore {

    override
    fun getCurrencyList(): Pair<DomainCurrency, List<DomainCurrency>> {
        val list = currencyDAO.getAll()
        val head = list[0]
        return Pair(head, list.subList(1, list.size))
    }

}