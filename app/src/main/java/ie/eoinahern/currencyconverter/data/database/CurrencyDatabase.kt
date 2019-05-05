package ie.eoinahern.currencyconverter.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency


@Database(entities = [DomainCurrency::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDAO(): CurrencyDAO
}