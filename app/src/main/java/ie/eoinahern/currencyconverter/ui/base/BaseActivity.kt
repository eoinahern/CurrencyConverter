package ie.eoinahern.currencyconverter.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        inject()
    }

    abstract fun inject()
    abstract fun getLayout(): Int
}
