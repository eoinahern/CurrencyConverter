package ie.eoinahern.currencyconverter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DomainCurrency(
    @PrimaryKey
    val currencySymbol: String,
    val amount: String,
    val flagRes: Int,
    val name: String
)