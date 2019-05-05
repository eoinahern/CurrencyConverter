package ie.eoinahern.currencyconverter.ui.details

import android.os.Bundle
import android.provider.Settings
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import ie.eoinahern.currencyconverter.R
import ie.eoinahern.currencyconverter.data.model.LatestCurrencies
import ie.eoinahern.currencyconverter.data.network.MyApi
import ie.eoinahern.currencyconverter.tools.ViewModelFactory
import ie.eoinahern.currencyconverter.ui.base.BaseActivity
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CurrencyConverterActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    @Inject
    lateinit var api: MyApi

    private lateinit var viewModel: CurrencyConverterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrencyConverterViewModel::class.java)


        val job = GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val call = api.getData()
                val resp = call.execute()
                if (resp.isSuccessful) {
                    println(resp.body())
                    val body = resp.body()
                    println(body)
                } else {
                    resp.message()
                }
            }
        }

    }

    override fun getLayout(): Int = R.layout.activity_currency_converter
}
