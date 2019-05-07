package ie.eoinahern.currencyconverter.ui.details

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import ie.eoinahern.currencyconverter.R
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import ie.eoinahern.currencyconverter.tools.ViewModelFactory
import ie.eoinahern.currencyconverter.ui.base.BaseActivity
import javax.inject.Inject

class CurrencyConverterActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: CurrencyConverterAdapter
    private lateinit var viewModel: CurrencyConverterViewModel
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrencyConverterViewModel::class.java)

        viewModel.getCurrencyList()
        observeCallState()
        observeFailure()
    }

    private fun observeCallState() {
        viewModel.observeData().observe(this, Observer<List<DomainCurrency>> {

        })
    }

    private fun observeFailure() {
        viewModel.getFailureResult().observe(this, Observer {

        })
    }

    private fun attachCurrencyList() {

    }

    override fun getLayout(): Int = R.layout.activity_currency_converter
}
