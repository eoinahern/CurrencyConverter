package ie.eoinahern.currencyconverter.data.model.mapper

import ie.eoinahern.currencyconverter.domain.model.DomainCurrency

object CurrencyMapper {

    fun mapToUIModel(inMap: Map<String, Double>?): List<DomainCurrency> {
        return inMap?.map {
            DomainCurrency(
                it.key, it.value,
                createFlagUrl(), matchCurrencySymbolTopCountry(it.key)
            )
        } ?: emptyList()
    }

    private fun createFlagUrl(): String {
        return "booxz"
    }

    private fun matchCurrencySymbolTopCountry(currency: String): String {
        return ""
    }

    private fun getNameOfCurrency(): String = "dollar"

}