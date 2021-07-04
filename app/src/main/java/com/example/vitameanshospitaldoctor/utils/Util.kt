package com.example.vitameanshospitaldoctor.utils

import android.content.Context
import android.util.DisplayMetrics
import kotlin.math.ceil
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.roundToInt

object Util {

    var mMetrics: DisplayMetrics ?= null

    fun init(context: Context){
        mMetrics = context.resources.displayMetrics
    }

    fun dpToPx(dp: Float): Float{
        return mMetrics?.let { dp * it.density } ?: dp
    }

    fun getDecimals(number: Float): Int{
        var i = roundToNextSignificant(number)
        if(i.isInfinite()) return 0
        return ceil(-log10(i)).toInt() + 2
    }

    fun roundToNextSignificant(number: Float): Float {
        if (number.isInfinite() || number.isNaN()) return 0f
        var d = ceil(log10(if (number < 0) -number else number))
        var pw = 1 - d.toInt()
        val magnitude = 10.0.pow(pw.toDouble()).toFloat()
        val shifted = (number * magnitude).roundToInt()
        return shifted / magnitude
    }
}