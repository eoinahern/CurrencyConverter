package ie.eoinahern.currencyconverter.data.repository.currency

import ie.eoinahern.currencyconverter.data.database.CurrencyDAO
import ie.eoinahern.currencyconverter.data.model.mapper.CurrencyMapper
import ie.eoinahern.currencyconverter.data.network.MyApi
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency


class NetworkDataStore constructor(private val api: MyApi, private val currencyDAO: CurrencyDAO) :
    CurrencyDataStore {

    /**
     * map to correct type
     */

    override fun getCurrencyList(): List<DomainCurrency> {
        val call = api.getData()
        val resp = call.execute()
        val rates = resp.body()?.rates
        val mappedDomain = CurrencyMapper.mapToUIModel(rates)
        currencyDAO.insertData(mappedDomain)
        return mappedDomain
    }

}