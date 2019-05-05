package ie.eoinahern.currencyconverter.data.database

import androidx.room.Dao
import androidx.room.Query
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency


@Dao
interface CurrencyDAO {

    @Query("Select * from DomainCurrency")
    fun getAll(): List<DomainCurrency>

    @Query("DELETE from DomainCurrency")
    fun deleteAll()


    @Query("SELECT COUNT(*) FROM DomainCurrency")
    fun countAll(): Int
}