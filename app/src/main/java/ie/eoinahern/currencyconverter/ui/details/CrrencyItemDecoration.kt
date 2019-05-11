package ie.eoinahern.currencyconverter.ui.details

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint

import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class CurrencyItemDecoration : RecyclerView.ItemDecoration {

    private val paint: Paint = Paint()
    private var inset: Float = 10f

    constructor(context: Context, color: Int, height: Float, inset: Float) {
        paint.color = ContextCompat.getColor(context, color)
        val thickness: Float = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            height, context.resources.displayMetrics
        )
        this.inset = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, inset,
            context.resources.displayMetrics
        )
        paint.strokeWidth = thickness
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0 until parent.childCount - 1) {
            val v = parent.getChildAt(i)
            c.drawLine(
                v.left.toFloat() + inset, v.bottom.toFloat(),
                v.right.toFloat() - inset, (v.bottom.toFloat()), paint
            )
        }
    }
}