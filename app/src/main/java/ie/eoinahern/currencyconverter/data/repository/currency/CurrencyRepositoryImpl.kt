package ie.eoinahern.currencyconverter.data.repository.currency

import ie.eoinahern.currencyconverter.data.repository.currency.CurrencyDataStoreFactory
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.domain.repository.Repository
import javax.inject.Inject


class CurrencyRepositoryImpl @Inject constructor(val currencyDataStoreFactory: CurrencyDataStoreFactory) :
    Repository<DomainCurrency> {

    override fun getData(): List<DomainCurrency> {
        return currencyDataStoreFactory.getCurrencyDatastore().getCurrencyList()
    }
}