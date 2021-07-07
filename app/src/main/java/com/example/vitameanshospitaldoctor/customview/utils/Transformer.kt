package com.example.vitameanshospitaldoctor.customview.utils

import android.graphics.Matrix
import android.graphics.PointF

class Transformer(
    var viewPortHandler: ViewPortHandler
) {

    var matrixValueToPx = Matrix()

    var matrixOffset = Matrix()

    var pixelToValueMatrixBuffer = Matrix()

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

    fun getValuesByTouchPoint(x: Float, y: Float): PointF{

        var result = PointF(0f,0f)
        getValuesByTouchPoint(x,y,result)
        return result
    }

    var ptsBuffer = FloatArray(2)
    fun getValuesByTouchPoint(x: Float, y: Float, result: PointF){

        ptsBuffer[0] = x
        ptsBuffer[1] = y

        pixelsToValue(ptsBuffer)
    }

    private fun pixelsToValue(pixels: FloatArray) {

        var tmp = pixelToValueMatrixBuffer
        tmp.reset()

        matrixOffset.invert(tmp)
        tmp.mapPoints(pixels)

        viewPortHandler.matrixTouch.invert(tmp)
        tmp.mapPoints(pixels)

        matrixValueToPx.invert(tmp)
        tmp.mapPoints(pixels)
    }

    fun prepareMatrixOffset(inverted: Boolean) {
        matrixOffset.reset()

        matrixOffset.postTranslate(viewPortHandler.contentRect.left, viewPortHandler.chartHeight - viewPortHandler.offsetBottom())
    }
}