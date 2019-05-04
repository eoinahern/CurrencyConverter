package ie.eoinahern.currencyconverter.ui.details

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import ie.eoinahern.currencyconverter.R
import ie.eoinahern.currencyconverter.tools.ViewModelFactory
import ie.eoinahern.currencyconverter.ui.base.BaseActivity
import javax.inject.Inject

class CurrencyConverterActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: CurrencyConverterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrencyConverterViewModel::class.java)
    }

    override fun getLayout(): Int = R.layout.activity_currency_converter
}
