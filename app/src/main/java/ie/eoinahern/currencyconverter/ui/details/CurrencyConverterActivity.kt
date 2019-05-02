package ie.eoinahern.currencyconverter.ui.details

import android.os.Bundle
import ie.eoinahern.currencyconverter.R
import ie.eoinahern.currencyconverter.ui.base.BaseActivity

class CurrencyConverterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun inject() {
        //not implemented
    }

    override fun getLayout(): Int = R.layout.activity_currency_converter
}
