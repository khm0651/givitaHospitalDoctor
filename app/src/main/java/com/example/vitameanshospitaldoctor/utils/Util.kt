package com.example.vitameanshospitaldoctor.utils

import android.content.Context
import android.util.DisplayMetrics

object Util {

    var mMetrics: DisplayMetrics ?= null

    fun init(context: Context){
        mMetrics = context.resources.displayMetrics
    }

    fun dpToPx(dp: Float): Float{
        return mMetrics?.let { dp * it.density } ?: dp
    }
}