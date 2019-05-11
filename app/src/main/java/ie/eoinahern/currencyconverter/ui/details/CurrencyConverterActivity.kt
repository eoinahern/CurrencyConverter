package ie.eoinahern.currencyconverter.ui.details

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import ie.eoinahern.currencyconverter.R
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.tools.CURRENCY_ITEM
import ie.eoinahern.currencyconverter.tools.LoadingView
import ie.eoinahern.currencyconverter.tools.ViewModelFactory
import ie.eoinahern.currencyconverter.ui.base.BaseActivity
import ie.eoinahern.currencyconverter.ui.selection.GraphActivity
import kotlinx.android.synthetic.main.activity_currency_converter.*
import javax.inject.Inject

class CurrencyConverterActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var currencyAdapter: CurrencyConverterAdapter
    private lateinit var viewModel: CurrencyConverterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrencyConverterViewModel::class.java)

        attachCurrencyList()
        viewModel.getCurrencyList()
        observeCallState()
        observeFailure()
    }

    private fun observeCallState() {
        viewModel.observeData().observe(this, Observer<Pair<DomainCurrency, List<DomainCurrency>>> {
            currencyAdapter.setList(it.second)
            setHeadElement(it.first)
            loading.setState(LoadingView.State.GONE)
        })
    }

    private fun setHeadElement(currency: DomainCurrency) {
        image.setImageResource(currency.flagRes)
        amountTxt.text = currency.amount
        symbolTxt.text = currency.currencySymbol
        name.text = currency.name
    }

    private fun observeFailure() {
        viewModel.getFailureResult().observe(this, Observer {
            loading.setState(LoadingView.State.FAILED)
        })
    }

    private fun attachCurrencyList() {
        recyclerView.addItemDecoration(CurrencyItemDecoration(this, R.color.colorAccent, 3f, 3f))
        recyclerView.adapter = currencyAdapter
        currencyAdapter.clickListener = ::navigateNext
    }

    private fun navigateNext(domainCurrency: DomainCurrency) {
        val intent = GraphActivity.getStartIntent(this)
        intent.putExtra(CURRENCY_ITEM, domainCurrency)
        startActivity(intent)
    }

    override fun getLayout(): Int = R.layout.activity_currency_converter
}
