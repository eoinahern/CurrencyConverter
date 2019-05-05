package ie.eoinahern.currencyconverter.data.repository

import ie.eoinahern.currencyconverter.domain.model.DomainCurrency


interface CurrencyDataStore {

    fun getCurrencyList(): List<DomainCurrency>

}