package ie.eoinahern.currencyconverter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DomainCurrency(
    @PrimaryKey
    val currencySymbol: String,
    val amount: Double,
    val flagURL: String,
    val name: String
)