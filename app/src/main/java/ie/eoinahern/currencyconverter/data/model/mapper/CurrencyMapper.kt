package ie.eoinahern.currencyconverter.data.model.mapper

import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import javax.inject.Singleton

object CurrencyMapper {

    fun mapToUIModel(inMap: Map<String, Double>?): List<DomainCurrency> {
        return listOf()
    }
}