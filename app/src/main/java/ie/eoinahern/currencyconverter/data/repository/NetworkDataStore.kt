package ie.eoinahern.currencyconverter.data.repository

import ie.eoinahern.currencyconverter.data.network.MyApi
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency


class NetworkDataStore constructor(private val api: MyApi) : CurrencyDataStore {



    override fun getCurrencyList(): List<DomainCurrency> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}