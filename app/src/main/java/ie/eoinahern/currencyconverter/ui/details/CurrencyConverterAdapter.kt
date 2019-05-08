package ie.eoinahern.currencyconverter.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.eoinahern.currencyconverter.R
import ie.eoinahern.currencyconverter.domain.model.DomainCurrency
import kotlinx.android.synthetic.main.currency_item_layyout.view.*
import javax.inject.Inject


class CurrencyConverterAdapter @Inject constructor() : RecyclerView.Adapter<CurrencyConverterAdapter.ViewHolder>() {

    private var list: MutableList<DomainCurrency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyConverterAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_item_layyout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CurrencyConverterAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.bindData(item)
    }

    fun setList(list: List<DomainCurrency>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bindData(item: DomainCurrency) {
            itemView.amountTxt.text = item.amount.toString()
            itemView.currencySymbolTxt.text = item.currencySymbol
            itemView.CurrencyNameTxt.text = item.name
            if (item.flagRes != -1)
                itemView.flag_icon.setImageResource(item.flagRes)
        }
    }

}