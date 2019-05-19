package ie.eoinahern.currencyconverter.ui.selection

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.mikephil.charting.data.Entry
import com.nhaarman.mockitokotlin2.any
import ie.eoinahern.currencyconverter.data.model.GraphNestedMap
import ie.eoinahern.currencyconverter.data.repository.graph.GraphDataRepositoryImpl
import ie.eoinahern.currencyconverter.domain.usecase.GetGraphData
import ie.eoinahern.currencyconverter.tools.Either
import ie.eoinahern.currencyconverter.tools.TWOFOUR_HOUR_KEY
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GraphActivtyViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var graphActivityViewModel: GraphActivtyViewModel
    private lateinit var getGraphData: GetGraphData

    @Mock
    private lateinit var repo: GraphDataRepositoryImpl

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getGraphData = GetGraphData(repo)
        graphActivityViewModel = GraphActivtyViewModel(getGraphData, TestCoroutineDispatcher())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetGraphData() {

        val testData: GraphNestedMap = mapOf(TWOFOUR_HOUR_KEY to listOf(Entry(10f, 10f)))

        val testsched = TestScheduler()

        runBlockingTest {
            Mockito.`when`(repo.getGraphData(any())).thenReturn(Either.Right(testData))
        }

        val pub = graphActivityViewModel.getGraphData()
        pub.observeOn(testsched)
            .subscribe({
                Assert.assertEquals(it[0].x, 10f)
            }, {
                it.printStackTrace()
            })

        runBlockingTest {
            graphActivityViewModel.getGraphData("")
        }

        testsched.triggerActions()
    }
}