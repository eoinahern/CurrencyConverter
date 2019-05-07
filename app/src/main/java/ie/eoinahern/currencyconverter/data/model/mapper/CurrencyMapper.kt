package ie.eoinahern.currencyconverter.data.model.mapper

import com.mynameismidori.currencypicker.ExtendedCurrency
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency

object CurrencyMapper {

    private val currencies = ExtendedCurrency.getAllCurrencies()

    fun mapToUIModel(inMapPrice: Map<String, Double>?, inMapName: Map<String, String>?): List<DomainCurrency> {
        return inMapPrice?.map {
            DomainCurrency(
                it.key,
                it.value,
                createFlagUrl(it.key),
                inMapName?.get(it.key) ?: ""
            )
        } ?: emptyList()
    }


    private fun createFlagUrl(currencySymbol: String): Int {
        return currencies.firstOrNull {
            it.code == currencySymbol
        }?.flag ?: -1
    }

}