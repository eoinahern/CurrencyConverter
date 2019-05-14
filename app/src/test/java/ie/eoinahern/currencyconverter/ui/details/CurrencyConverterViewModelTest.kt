package ie.eoinahern.currencyconverter.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ie.eoinahern.currencyconverter.domain.BaseUsecase
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.domain.usecase.GetCurrency
import ie.eoinahern.currencyconverter.tools.Either
import kotlinx.coroutines.CoroutineScope
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(MockitoJUnitRunner::class)
class CurrencyConverterViewModelTest {


    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var currencyViewModel: CurrencyConverterViewModel

    @Mock
    private lateinit var mockUsecase: GetCurrency

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        currencyViewModel = CurrencyConverterViewModel(mockUsecase, Dispatchers.Unconfined)
    }

    @Test
    fun testGetCurrency() {

        val item: Pair<DomainCurrency, List<DomainCurrency>> = Pair(
            DomainCurrency("USD", "1.00", 2, "US DOLLAR"),
            listOf(DomainCurrency("EUR", "12.00", 1, "EURO"))
        )

        given {
            runBlocking { mockUsecase.executeUsecase(any()) }
        }.willReturn(Either.Right(item))


        currencyViewModel.observeData().observeForever {
            val domain = it.first
            assertEquals(domain.amount, "zzzzz")
            assertEquals(domain.currencySymbol, "cccc")
        }


        runBlocking {
            currencyViewModel.getCurrencyList()
        }

    }
}