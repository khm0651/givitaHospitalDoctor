package com.example.vitameanshospitaldoctor.customview.utils

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.example.vitameanshospitaldoctor.customview.FSize
import com.example.vitameanshospitaldoctor.customview.PointF
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

object Utils {

    const val FDEG2RAD = Math.PI / 180f

    private val calcTextHeightRect = Rect()

    fun calcTextHeight(paint: Paint, demoText: String): Int{

        var r = calcTextHeightRect
        r.set(0,0,0,0)
        paint.getTextBounds(demoText,0, demoText.length, r)
        return r.height()
    }

    fun calcTextWidth(paint: Paint, demoText: String): Float{
        return paint.measureText(demoText)
    }

    var drawTextRectBuffer = Rect()
    var fontMetricsBuffer = Paint.FontMetrics()

    fun drawxAxisValue(
        c: Canvas,
        label: String,
        x: Float,
        y: Float,
        paint: Paint,
        anchor: PointF,
        labelRotationAngleDegrees: Float
    ) {

        var drawOffSetX = 0f
        var drawOffSetY = 0f

        var lineHeight = paint.getFontMetrics(fontMetricsBuffer)
        paint.getTextBounds(label, 0, label.length, drawTextRectBuffer)

        drawOffSetX -= drawTextRectBuffer.left
        drawOffSetY += -fontMetricsBuffer.ascent

        var originalTextAlign = paint.textAlign
        paint.textAlign = Paint.Align.LEFT

        if(anchor.x != 0f || anchor.y != 0f){

            drawOffSetX -= drawTextRectBuffer.width() * anchor.x
            drawOffSetY -= lineHeight * anchor.y
        }

        drawOffSetX += x
        drawOffSetY += y

        c.drawText(label, drawOffSetX, drawOffSetY, paint)
        paint.textAlign = originalTextAlign
    }

    fun calcTextSize(paint: Paint, text: String): FSize {
        var result = FSize(0f,0f)
        calcTextSize(paint,text,result)
        return result
    }

    var calcTextSizeRect = Rect()

    fun calcTextSize(paint: Paint, text: String, outputFSize: FSize){

        var r = calcTextHeightRect
        r.set(0,0,0,0)
        paint.getTextBounds(text,0,text.length,r)
        outputFSize.width = r.width().toFloat()
        outputFSize.height = r.height().toFloat()
    }

    fun getSizeOfRotatedRectangleByDegrees(rectangleWidth: Float, rectangleHeight: Float, degrees: Float): FSize {
        var radians = degrees * FDEG2RAD
        return getSizeOfRotatedRectangleByRadians(rectangleWidth, rectangleHeight, radians)

    }

    private fun getSizeOfRotatedRectangleByRadians(rectangleWidth: Float, rectangleHeight: Float, radians: Double): FSize {
        return FSize(
            abs(rectangleWidth * cos(radians).toFloat() + abs(rectangleHeight * sin(radians).toFloat())),
            abs(rectangleWidth * sin(radians).toFloat() + abs(rectangleHeight * cos(radians).toFloat()))
        )
    }
}