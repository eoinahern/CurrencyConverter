package ie.eoinahern.currencyconverter.data.database

import androidx.room.*
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency


@Dao
interface CurrencyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: List<DomainCurrency>)

    @Query("Select * from DomainCurrency")
    fun getAll(): List<DomainCurrency>

    @Query("DELETE from DomainCurrency")
    fun deleteAll()


    @Query("SELECT COUNT(*) FROM DomainCurrency")
    fun countAll(): Int
}