package ie.eoinahern.currencyconverter.data.repository.currency

import ie.eoinahern.currencyconverter.domain.model.DomainCurrency

/**
 * head element in seperate view.
 * not passed to recyclerview
 *
 *
 */

interface CurrencyDataStore {
    fun getCurrencyList(): Pair<DomainCurrency, List<DomainCurrency>>
}