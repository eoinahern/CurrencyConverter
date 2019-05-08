package ie.eoinahern.currencyconverter.ui.details

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import ie.eoinahern.currencyconverter.R
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.tools.ViewModelFactory
import ie.eoinahern.currencyconverter.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_currency_converter.*
import javax.inject.Inject

class CurrencyConverterActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var currencyAdapter: CurrencyConverterAdapter
    private lateinit var viewModel: CurrencyConverterViewModel
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }


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
        viewModel.observeData().observe(this, Observer<List<DomainCurrency>> {
            currencyAdapter.setList(it)
        })
    }

    private fun observeFailure() {
        viewModel.getFailureResult().observe(this, Observer {
        })
    }

    private fun attachCurrencyList() {
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = currencyAdapter
        }
    }

    override fun getLayout(): Int = R.layout.activity_currency_converter
}
