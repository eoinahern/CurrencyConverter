package ie.eoinahern.currencyconverter.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.eoinahern.currencyconverter.R
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import javax.inject.Inject


class CurrencyConverterAdapter @Inject constructor() : RecyclerView.Adapter<CurrencyConverterAdapter.ViewHolder>() {

    private lateinit var list: List<DomainCurrency>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyConverterAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_item_layyout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CurrencyConverterAdapter.ViewHolder, position: Int) {

    }

    fun setList(list: List<DomainCurrency>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        init {

        }

    }

}