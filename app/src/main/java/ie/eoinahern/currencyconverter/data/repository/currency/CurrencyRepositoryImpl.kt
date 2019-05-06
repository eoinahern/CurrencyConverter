package ie.eoinahern.currencyconverter.data.repository.currency

import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.domain.repository.Repository
import javax.inject.Inject


class CurrencyRepositoryImpl @Inject constructor(val currencyDataStoreFactory: CurrencyDataStoreFactory) :
    Repository<List<DomainCurrency>> {


    //repository returns Either type!!


    override fun getData(): List<DomainCurrency> {
        return currencyDataStoreFactory.getCurrencyDatastore().getCurrencyList()
    }


}