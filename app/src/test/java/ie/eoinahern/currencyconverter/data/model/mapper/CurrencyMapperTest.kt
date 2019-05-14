package ie.eoinahern.currencyconverter.data.model.mapper

import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.tools.EURO_KEY
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CurrencyMapperTest {

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        AndroidThreeTen.init(context)
    }


    @Test
    fun test() {
        val pairs = CurrencyMapper.mapToUIModel(
            mapOf("hello" to 211f.toDouble()),
            mapOf("hello" to "hello currency")
        )

        assertEquals(pairs.first.currencySymbol, EURO_KEY)
        assert(pairs.second is List<DomainCurrency>)
        assertEquals(pairs.second[0].name, "hello currency")
    }


}