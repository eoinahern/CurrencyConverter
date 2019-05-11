package ie.eoinahern.currencyconverter.tools

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import ie.eoinahern.currencyconverter.R


class LoadingView : FrameLayout {

    lateinit var loadingView: LinearLayout
    lateinit var errorView: LinearLayout

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        val view = inflate(context, R.layout.loading_view_layout, this)
        loadingView = view.findViewById(R.id.progress_view)
        errorView = view.findViewById(R.id.error_view)
    }

    fun setState(stateIn: State) {
        when (stateIn) {
            State.LOADING -> {
                loadingView.visibility = View.VISIBLE
                errorView.visibility = View.GONE
            }
            State.FAILED -> {
                loadingView.visibility = View.GONE
                errorView.visibility = View.VISIBLE
            }
            State.GONE -> {
                visibility = View.GONE
            }
        }
    }

    enum class State {
        LOADING,
        FAILED,
        GONE
    }
}