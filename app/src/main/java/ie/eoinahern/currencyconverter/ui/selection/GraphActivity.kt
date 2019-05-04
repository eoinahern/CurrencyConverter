package ie.eoinahern.currencyconverter.ui.selection

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import ie.eoinahern.currencyconverter.R
import ie.eoinahern.currencyconverter.tools.ViewModelFactory
import ie.eoinahern.currencyconverter.ui.base.BaseActivity
import javax.inject.Inject

class GraphActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(GraphActivtyViewModel::class.java)

    }


    override fun getLayout(): Int = R.layout.activity_graph
}
