package ie.eoinahern.currencyconverter.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ie.eoinahern.currencyconverter.data.repository.currency.CurrencyRepositoryImpl
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.domain.usecase.GetCurrency
import ie.eoinahern.currencyconverter.tools.Either
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CurrencyConverterViewModelTest {


    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var currencyViewModel: CurrencyConverterViewModel


    private lateinit var mockGetCurrency: GetCurrency

    @Mock
    private lateinit var mockRepo: CurrencyRepositoryImpl

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockGetCurrency = GetCurrency(mockRepo)
        currencyViewModel = CurrencyConverterViewModel(mockGetCurrency, TestCoroutineDispatcher())
    }


    /**
     * test not executing observeForever block. need to
     * Investigate issue further.
     */

    @ExperimentalCoroutinesApi
    @Test
    fun testGetCurrency() {

        val item: Pair<DomainCurrency, List<DomainCurrency>> = Pair(
            DomainCurrency("USD", "1.00", 2, "US DOLLAR"),
            listOf(DomainCurrency("EUR", "12.00", 1, "EURO"))
        )

        runBlockingTest {
            Mockito.`when`(mockRepo.getData()).thenReturn(Either.Right(item))
        }


        currencyViewModel.observeData().observeForever {
            val domain = it.first
            assertEquals(domain.amount, "1.00")
            assertEquals(domain.currencySymbol, "USD")
        }



        runBlockingTest {
            currencyViewModel.getCurrencyList()
        }

    }
}