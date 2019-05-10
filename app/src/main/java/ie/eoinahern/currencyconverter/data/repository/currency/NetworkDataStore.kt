package ie.eoinahern.currencyconverter.data.repository.currency


import ie.eoinahern.currencyconverter.data.database.CurrencyDAO
import ie.eoinahern.currencyconverter.data.model.mapper.CurrencyMapper
import ie.eoinahern.currencyconverter.data.network.MyApi
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import retrofit2.Call


class NetworkDataStore constructor(private val api: MyApi, private val currencyDAO: CurrencyDAO) :
    CurrencyDataStore {

    override fun getCurrencyList(): Pair<DomainCurrency, List<DomainCurrency>> {
        val domainCurrencies = api.getAllSymbls().execute().body()?.symbols
        val countryNames = api.getData().execute().body()?.rates

        val mappedDomain = CurrencyMapper
            .mapToUIModel(
                requireNotNull(countryNames),
                requireNotNull(domainCurrencies)
            )

        currencyDAO.insertData(revertToList(mappedDomain))
        return mappedDomain
    }

    private fun revertToList(pair: Pair<DomainCurrency, List<DomainCurrency>>): List<DomainCurrency> {
        val list = pair.second.toMutableList()
        list.add(0, pair.first)
        return list
    }

}