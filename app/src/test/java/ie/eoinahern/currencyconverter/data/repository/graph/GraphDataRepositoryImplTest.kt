package ie.eoinahern.currencyconverter.data.repository.graph

import ie.eoinahern.currencyconverter.data.model.GraphNestedMap
import ie.eoinahern.currencyconverter.domain.exception.Failure
import ie.eoinahern.currencyconverter.tools.Either
import org.junit.Assert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException


@RunWith(MockitoJUnitRunner::class)
class GraphDataRepositoryImplTest {

    private lateinit var graphRepo: GraphDataRepositoryImpl

    @Mock
    private lateinit var factory: GraphDataStorFactory

    @Mock
    private lateinit var dataSource: GraphDataSource

    @Mock
    private lateinit var mockGraph: GraphNestedMap

    @Mock
    private lateinit var mockException: IOException

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        graphRepo = GraphDataRepositoryImpl(factory)
    }

    @Test
    fun test() {
        Mockito.`when`(factory.getDataStore()).thenReturn(dataSource)
        Mockito.`when`(dataSource.getList(Mockito.anyString())).thenReturn(mockGraph)
        val either = graphRepo.getGraphData("")
        verify(factory).getDataStore()
        verify(dataSource).getList(Mockito.anyString())
        assert(either.isRight())

    }


    @Test
    fun testException() {
        try {
            Mockito.`when`(factory.getDataStore()).thenThrow(IOException())
            Mockito.`when`(dataSource.getList(Mockito.anyString())).thenReturn(mockGraph)
            val item = graphRepo.getGraphData("")
            verify(dataSource, never()).getList(Mockito.anyString())
            assert(item.isLeft())

        } catch (e: Exception) {

        }
    }
}