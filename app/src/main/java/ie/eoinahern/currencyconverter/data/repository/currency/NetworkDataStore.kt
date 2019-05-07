package ie.eoinahern.currencyconverter.data.repository.currency


import ie.eoinahern.currencyconverter.data.database.CurrencyDAO
import ie.eoinahern.currencyconverter.data.model.mapper.CurrencyMapper
import ie.eoinahern.currencyconverter.data.network.MyApi
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import retrofit2.Call


class NetworkDataStore constructor(private val api: MyApi, private val currencyDAO: CurrencyDAO) :
    CurrencyDataStore {

    /**
     * map to correct type
     */

    override fun getCurrencyList(): List<DomainCurrency> {
        val call = api.getData()

        val domainCurrencies = convertCallToType(api.getAllSymbls())?.symbols
        val countryNames = convertCallToType(api.getData())?.rates

        val mappedDomain = CurrencyMapper.mapToUIModel(countryNames, domainCurrencies)
        currencyDAO.insertData(mappedDomain)
        return mappedDomain
    }


    private inline fun <T> convertCallToType(call: Call<T>): T? {
        val resp = call.execute()
        return resp.body()
    }

}