package ie.eoinahern.currencyconverter.ui.selection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.android.AndroidInjection
import ie.eoinahern.currencyconverter.R
import ie.eoinahern.currencyconverter.data.model.GraphNestedMap
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.tools.*
import ie.eoinahern.currencyconverter.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_graph.*
import javax.inject.Inject

class GraphActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(GraphActivtyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setActionBar()
        observeSelection()
        observeFailure()
        getIntentData()
    }

    private fun setActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getIntentData() {
        val passedCurrency = intent.getParcelableExtra<DomainCurrency>(CURRENCY_ITEM)
        setTopElements(passedCurrency)
        getGraph(passedCurrency.currencySymbol)
    }

    private fun setTopElements(domainCurrency: DomainCurrency) {
        convertedFlag.setImageResource(domainCurrency.flagRes)
        convertedPrice.text = domainCurrency.amount
        convertedSymbol.text = domainCurrency.currencySymbol
    }

    private fun getGraph(symbol: String) {
        viewModel.getGraphData(symbol)
    }

    private fun observeSelection() {
        viewModel.liveGrapData().observe(this, Observer {
            loading.setState(LoadingView.State.GONE)
            setListeners()
            setLinegraph(it[SIX_MONTH_KEY])
        })
    }

    private fun observeFailure() {
        viewModel.getFailureResult().observe(this, Observer {
            loading.setState(LoadingView.State.FAILED)
        })
    }

    private fun setLinegraph(entryList: List<Entry>?) {
        val dataSet = LineDataSet(entryList, getString(R.string.price_txt))
        dataSet.color = getColor(R.color.colorPrimary)
        dataSet.valueTextColor = getColor(R.color.colorPrimary)
        dataSet.setDrawCircles(false)
        dataSet.setDrawFilled(true)
        dataSet.fillDrawable = getDrawable(R.drawable.graph_gradient)
        linegraph.xAxis.setDrawLabels(false)
        linegraph.data = LineData(dataSet)
        linegraph.invalidate()
    }

    private fun setListeners() {

    }

    companion object {
        fun getStartIntent(context: Context): Intent = Intent(context, GraphActivity::class.java)
    }

    override fun getLayout(): Int = R.layout.activity_graph
}
