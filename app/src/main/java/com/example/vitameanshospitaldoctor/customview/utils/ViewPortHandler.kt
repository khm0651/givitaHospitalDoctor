package com.example.vitameanshospitaldoctor.customview.utils

import android.graphics.Matrix
import android.graphics.RectF

class ViewPortHandler {

    var matrixTouch = Matrix()

    var contentRect = RectF()

    var chartWidth = 0f

    var chartHeight = 0f

    var minScaleY = 1f

    var maxScaleY = Float.MAX_VALUE

    var minScaleX = 1f

    var maxScaleX = Float.MAX_VALUE

    var scaleY = 1f

    var transX = 0f

    var transY = 0f

    var transOffsetX = 0f

    var transOffsetY = 0f

    fun contentWidth(): Float{
        return contentRect.width()
    }

    fun contentHeight(): Float{
        return contentRect.height()
    }

    fun isInBoundsLeft(x: Float): Boolean{
        return contentRect.left <= x + 1
    }

    fun isInBoundsRight(x: Float): Boolean{
        return contentRect.right >= ((x * 100f).toInt() / 100f) - 1
    }
}