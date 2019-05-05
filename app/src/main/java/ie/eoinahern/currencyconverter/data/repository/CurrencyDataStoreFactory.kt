package ie.eoinahern.currencyconverter.data.repository

import android.content.SharedPreferences
import ie.eoinahern.currencyconverter.data.database.CurrencyDAO
import ie.eoinahern.currencyconverter.data.network.MyApi
import javax.inject.Inject
import javax.inject.Singleton


/**
 *
 *
 * create different implementations of CurrencyDataStore
 */

@Singleton
class CurrencyDataStoreFactory @Inject constructor(
    api: MyApi, currencyDao: CurrencyDAO,
    private val sharepPrefs: SharedPreferences
) {

    private val networkDataStore: NetworkDataStore
    private val cacheDataStore: CacheDataStore

    init {
        cacheDataStore = CacheDataStore(currencyDao)
        networkDataStore = NetworkDataStore(api)
    }

    /**
     * check DB empty. check time expired
     * if either, use APi, else
     * return cache
     */

    fun getCurrencyDatastore(): CurrencyDataStore {
        return networkDataStore
    }


    fun getCacheDataStore(): CurrencyDataStore = cacheDataStore

    fun getAPIDataStore(): CurrencyDataStore = networkDataStore


}