package ie.eoinahern.currencyconverter.data.repository.currency

import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.tools.Either


interface CurrencyDataStore {
    fun getCurrencyList(): List<DomainCurrency>
}