package ie.eoinahern.currencyconverter.data.repository.currency


import android.content.SharedPreferences
import ie.eoinahern.currencyconverter.data.database.CurrencyDAO
import ie.eoinahern.currencyconverter.data.model.mapper.CurrencyMapper
import ie.eoinahern.currencyconverter.data.network.MyApi
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.tools.SAVED_DATE
import org.threeten.bp.LocalDateTime
import retrofit2.Call
import javax.inject.Inject


class NetworkDataStore constructor(
    private val api: MyApi, private val currencyDAO: CurrencyDAO,
    private val sharedPrefsEdit: SharedPreferences.Editor
) :
    CurrencyDataStore {

    override fun getCurrencyList(): Pair<DomainCurrency, List<DomainCurrency>> {
        val domainCurrencies = api.getAllSymbls().execute().body()?.symbols
        val countryNames = api.getData().execute().body()?.rates

        val mappedDomain = CurrencyMapper
            .mapToUIModel(
                requireNotNull(countryNames),
                requireNotNull(domainCurrencies)
            )

        saveDateCached()
        saveData(mappedDomain)
        return mappedDomain
    }

    private fun revertToList(pair: Pair<DomainCurrency, List<DomainCurrency>>): List<DomainCurrency> {
        val list = pair.second.toMutableList()
        list.add(0, pair.first)
        return list
    }

    private fun saveDateCached() {
        sharedPrefsEdit.putString(SAVED_DATE, LocalDateTime.now().toString()).commit()
    }

    private fun saveData(mappedDomain: Pair<DomainCurrency, List<DomainCurrency>>) {
        currencyDAO.insertData(revertToList(mappedDomain))
    }

}