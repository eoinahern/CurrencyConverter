package ie.eoinahern.currencyconverter.data.model.mapper

import com.mynameismidori.currencypicker.ExtendedCurrency
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.tools.EMPTY_STRING
import ie.eoinahern.currencyconverter.tools.EURO_KEY
import java.text.NumberFormat
import java.util.*

object CurrencyMapper {

    private val currencies = ExtendedCurrency.getAllCurrencies()
    private val formatter: NumberFormat by lazy {
        NumberFormat.getInstance(Locale.getDefault()).apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 4
        }
    }

    fun mapToUIModel(
        inMapPrice: Map<String, Double>, inMapName: Map<String, String>
    ): Pair<DomainCurrency, List<DomainCurrency>> {

        val list = inMapPrice.filter { it.key != EURO_KEY }
            .map {
                DomainCurrency(
                    it.key, getCurrencyFormat(it.value), createFlagUrl(it.key),
                    inMapName[it.key] ?: EMPTY_STRING
                )
            }

        return Pair(
            DomainCurrency(
                EURO_KEY, getCurrencyFormat(inMapPrice[EURO_KEY] ?: 0.0),
                createFlagUrl(EURO_KEY), inMapName[EURO_KEY] ?: EMPTY_STRING
            ), list
        )
    }

    private fun createFlagUrl(currencySymbol: String): Int {
        return currencies.firstOrNull {
            it.code == currencySymbol
        }?.flag ?: -1
    }

    private fun getCurrencyFormat(amount: Double): String {
        return formatter.format(amount)
    }

}