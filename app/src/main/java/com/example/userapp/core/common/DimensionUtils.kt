package com.example.userapp.core.common

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import java.text.DecimalFormat

object DimensionUtil {
    fun dp2px(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    fun Number.dpToPx(context: Context? = null): Float =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )
    fun Double.formatCurrency(): String {
        return String.format("%.1f", this)
    }

    fun Double?.decimalFormat(forceComma: Boolean = false): String {
        val decimalFormatForceComma = DecimalFormat("#,##0")

        this?.let { value ->
            return decimalFormatForceComma.format(value).toString()
        } ?: run {
            return ""
        }
    }

    fun Double.format(): String {
        return if (this.compareTo(this.toLong()) == 0) {
            String.format("%d", this.toLong())
        } else {
            String.format("%s", this)
        }
    }

    fun Double.format1(): Any {
        return if (this.compareTo(this.toLong()) == 0) {
            this.toLong()
        } else {
            this
        }
    }

    fun Float.format(): String {
        return if (this.compareTo(this.toLong()) == 0) {
            String.format("%d", this.toLong())
        } else {
            String.format("%s", this)
        }
    }

    fun Double.formatMinute(): String {
        val hour: String = String.format("%d", this.toLong() / 60)
        val minute: String = String.format("%d", this.toLong() % 60)
        return String.format("%s:%s", hour.padStart(2, '0'), minute.padStart(2, '0'))
    }
}
