package com.example.vitameanshospitaldoctor.customview.utils

import android.graphics.Paint
import android.graphics.Rect

object Utils {

    private val calcTextHeightRect = Rect()

    fun calcTextHeight(paint: Paint, demoText: String): Int{

        var r = calcTextHeightRect
        r.set(0,0,0,0)
        paint.getTextBounds(demoText,0, demoText.length, r)
        return r.height()
    }
}