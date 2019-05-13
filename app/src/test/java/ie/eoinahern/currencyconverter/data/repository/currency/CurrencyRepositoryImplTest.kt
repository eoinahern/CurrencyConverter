package ie.eoinahern.currencyconverter.data.repository.currency

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException


@RunWith(MockitoJUnitRunner::class)
class CurrencyRepositoryImplTest {


    private lateinit var currencyRepo: CurrencyRepositoryImpl

    @Mock
    private lateinit var currencyFactory: CurrencyDataStoreFactory

    @Mock
    lateinit var mockCurrencyDataStore: CurrencyDataStore

    @Mock
    lateinit var mockCurrencyPair: Pair<DomainCurrency, List<DomainCurrency>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        currencyRepo = CurrencyRepositoryImpl(currencyFactory)
    }

    @Test
    fun testNormalExecution() {
        Mockito.`when`(currencyFactory.getCurrencyDatastore()).thenReturn(mockCurrencyDataStore)
        Mockito.`when`(mockCurrencyDataStore.getCurrencyList()).thenReturn(mockCurrencyPair)
        val result = currencyRepo.getData()

        Mockito.verify(currencyFactory).getCurrencyDatastore()
        Mockito.verify(mockCurrencyDataStore).getCurrencyList()
        assert(result.isRight())
    }

    @Test
    fun testException() {

        try {
            Mockito.`when`(currencyFactory.getCurrencyDatastore()).thenThrow(IOException())
            Mockito.`when`(mockCurrencyDataStore.getCurrencyList()).thenReturn(mockCurrencyPair)
            val result = currencyRepo.getData()
            assert(result.isLeft())
            assert(result is IOException)
        } catch (e: Exception) {
        }

    }
}