package ie.eoinahern.currencyconverter.data.repository.currency

import android.content.SharedPreferences
import ie.eoinahern.currencyconverter.data.database.CurrencyDAO
import ie.eoinahern.currencyconverter.data.network.MyApi
import ie.eoinahern.currencyconverter.domain.BaseUsecase
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.tools.DateUtil
import ie.eoinahern.currencyconverter.tools.EMPTY_STRING
import ie.eoinahern.currencyconverter.tools.Either
import ie.eoinahern.currencyconverter.tools.SAVED_DATE
import org.threeten.bp.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton


/**
 * create different implementations of CurrencyDataStore.
 * composed in the Factory class
 */

@Singleton
class CurrencyDataStoreFactory @Inject constructor(
    api: MyApi,
    currencyDao: CurrencyDAO,
    private val dateUtil: DateUtil,
    private val sharedPrefs: SharedPreferences,
    sharedPrefsEdit: SharedPreferences.Editor
) {

    private val networkDataStore: NetworkDataStore = NetworkDataStore(api, currencyDao, sharedPrefsEdit)
    private val cacheDataStore: CacheDataStore = CacheDataStore(currencyDao)

    /**
     * check DB empty. check time expired
     * if either, use APi, else
     * return cache
     */

    fun getCurrencyDatastore(): CurrencyDataStore {
        return if (determineValid(convertDateString(retrieveDateString()))) {
            cacheDataStore
        } else {
            networkDataStore
        }
    }

    private fun retrieveDateString(): String? {
        return sharedPrefs.getString(SAVED_DATE, EMPTY_STRING)
    }

    private fun convertDateString(str: String?): Either<BaseUsecase.None, LocalDateTime> {
        return try {
            val date = dateUtil.parseDataTimeString(str ?: EMPTY_STRING)
            Either.Right(date)
        } catch (exception: Exception) {
            Either.Left(BaseUsecase.None())
        }
    }

    private fun determineValid(either: Either<BaseUsecase.None, LocalDateTime>): Boolean {
        return when (either) {
            is Either.Left -> false
            is Either.Right -> dateUtil.checkSaveDateValid(either.b, 1)
        }
    }
}