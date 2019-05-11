package ie.eoinahern.currencyconverter.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class DomainCurrency(
    @PrimaryKey
    val currencySymbol: String,
    val amount: String,
    val flagRes: Int,
    val name: String
) : Parcelable