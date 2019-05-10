package ie.eoinahern.currencyconverter.ui.details

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ie.eoinahern.currencyconverter.R

class CurrencyItemDecoration constructor(val context: Context) : RecyclerView.ItemDecoration() {

    private val color = ContextCompat.getColor(context, R.color.colorPrimary)
    private val paint: Paint = Paint().apply {
        style = Paint.Style.FILL
    }

    init {
        paint.color = color
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val count = parent.childCount

        for (i in 1.until(count - 1)) {
            val child = parent.getChildAt(i)
            val height = child.height
            val width = child.width
            c.drawLine(0f, child.height - 10f, child.width.toFloat(), child.height - 10f, paint)
        }
    }


}