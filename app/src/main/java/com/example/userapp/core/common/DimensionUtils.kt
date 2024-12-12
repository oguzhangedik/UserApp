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
}
