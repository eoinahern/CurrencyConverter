package ie.eoinahern.currencyconverter.ui.selection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.eoinahern.currencyconverter.R
import ie.eoinahern.currencyconverter.ui.base.BaseActivity

class GraphActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun inject() {
        //dont do anything yet
    }

    override fun getLayout(): Int = R.layout.activity_graph
}
