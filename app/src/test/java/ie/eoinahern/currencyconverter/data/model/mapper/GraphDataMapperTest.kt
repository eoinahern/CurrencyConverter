package ie.eoinahern.currencyconverter.data.model.mapper

import android.content.Context
import com.github.mikephil.charting.data.Entry
import com.jakewharton.threetenabp.AndroidThreeTen
import ie.eoinahern.currencyconverter.data.model.GraphData
import ie.eoinahern.currencyconverter.data.model.GraphNestedMap
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GraphDataMapperTest {

    @Mock
    private lateinit var context: Context


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        AndroidThreeTen.init(context)
    }

    @Test
    fun mapDataTest() {

        val nestedGraph = GraphDataMapper.mapData(
            GraphData(
                true,
                mapOf("2020-11-11" to mapOf("AED" to 12.00))
            ), mapOf(Pair("NOW", "2019-05-14"))
        )


        assert(nestedGraph is GraphNestedMap)
        assertTrue(nestedGraph.containsKey("NOW"))
        assertTrue(nestedGraph["NOW"] is List<Entry>)
    }
}