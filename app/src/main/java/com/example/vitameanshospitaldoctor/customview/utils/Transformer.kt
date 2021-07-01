package com.example.vitameanshospitaldoctor.customview.utils

import android.graphics.Matrix

class Transformer(
    var viewPortHandler: ViewPortHandler
) {

    var matrixValueToPx = Matrix()

    var matrixOffset = Matrix()

    fun prepareMatrixValuePx(xChartMin: Float, deltaX: Float, deltaY: Float, yChartMin: Float){
        var scaleX: Float = viewPortHandler.contentWidth() / deltaX
        var scaleY: Float = viewPortHandler.contentHeight() / deltaY

        if(scaleX.isInfinite()) scaleX = 0f
        if(scaleY.isInfinite()) scaleY = 0f

        matrixValueToPx.reset()
        matrixValueToPx.postTranslate(-xChartMin, -yChartMin)
        matrixValueToPx.postScale(scaleX, -scaleY)

    }

    fun pointValuesToPixel(pts: FloatArray) {

        matrixValueToPx.mapPoints(pts)
        viewPortHandler.matrixTouch.mapPoints(pts)
        matrixOffset.mapPoints(pts)
    }
}