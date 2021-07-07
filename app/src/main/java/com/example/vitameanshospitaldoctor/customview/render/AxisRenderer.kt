package com.example.vitameanshospitaldoctor.customview.render

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.vitameanshospitaldoctor.customview.components.AxisBase
import com.example.vitameanshospitaldoctor.customview.utils.Transformer
import com.example.vitameanshospitaldoctor.customview.utils.ViewPortHandler
import com.example.vitameanshospitaldoctor.utils.Util
import kotlin.math.*

abstract class AxisRenderer(
    var axis: AxisBase,
    var trans: Transformer,
    viewPortHandler: ViewPortHandler,
) : Renderer(viewPortHandler) {

    lateinit var gridPaint: Paint

    lateinit var axisLabelPaint: Paint

    lateinit var axisLinePaint: Paint

    lateinit var limitLinePaint: Paint

    init {

        axisLabelPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        axisLabelPaint.color = Color.BLACK
        axisLabelPaint.textSize = Util.dpToPx(10f)

        gridPaint = Paint()
        gridPaint.color = Color.GRAY
        gridPaint.strokeWidth = 1f
        gridPaint.style = Paint.Style.STROKE
        gridPaint.alpha = 90

        axisLinePaint = Paint()
        axisLinePaint.color = Color.BLACK
        axisLinePaint.strokeWidth = 1f
        axisLinePaint.style = Paint.Style.STROKE

        limitLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        limitLinePaint.style = Paint.Style.STROKE
    }

    open fun computeAxis(min: Float, max: Float, inverted: Boolean){

        var t_min = min
        var t_max = max
        if(viewPortHandler.contentWidth() > 10 && viewPortHandler.isFullyZoomedOutY()){

            var p1 = trans.getValuesByTouchPoint(viewPortHandler.contentRect.left, viewPortHandler.contentRect.top)
            var p2 = trans.getValuesByTouchPoint(viewPortHandler.contentRect.left, viewPortHandler.contentRect.bottom)

            if(!inverted){

                t_min = p2.y
                t_max = p1.y
            }else{

                t_min = p1.y
                t_max = p2.y
            }
        }

        computeAxisValues(t_min,t_max)
    }

    open fun computeAxisValues(min: Float, max: Float) {
        var yMin = min
        var yMax = max

        var labelCount = axis.labelCount
        var range = abs(yMax - yMin)

        if(labelCount == 0 || range<=0 || range.isInfinite()){
            axis.entries = floatArrayOf()
            axis.centeredEntries = floatArrayOf()
            axis.entryCount = 0
            return
        }

        var rawInterval = range / labelCount
        var interval = Util.roundToNextSignificant(rawInterval)

        var intervalMagnitude = Util.roundToNextSignificant((10.0).pow(log10(interval.toDouble())).toFloat())
        var intervalSigDigit = (interval / intervalMagnitude).toInt()
        if( intervalSigDigit > 5){
            interval = if(floor(10.0 * intervalMagnitude) == 0.0 ) interval else floor(10.0 * intervalMagnitude).toFloat()
        }

        var n = if(axis.isCenterAxisLabelsEnabled) 1 else 0

        if(axis.forceLabels){

            interval = range/ (labelCount - 1).toFloat()
            axis.entryCount = labelCount

            if(axis.entries.size < labelCount){
                axis.entries = FloatArray(labelCount)
            }

            var v = min

            for(i in 0 until labelCount){
                axis.entries[i] = v
                v+= interval
            }

            n = labelCount
        }else{

            var first = if(interval == 0f) 0f else ceil(yMin / interval) * interval

            var last = if(interval == 0f) 0f else (floor(yMax / interval) * interval).nextUp()

            var f: Float

            if (interval != 0f && last != first) {
                f = first
                while (f <= last) {
                    ++n
                    f += interval
                }
            } else if (last == first && n == 0) {
                n = 1
            }

            axis.entryCount = n

            if (axis.entries.size < n) {
                // Ensure stops contains at least numStops elements.
                axis.entries = FloatArray(n)
            }


            f = first
            var i: Int = 0
            while (i < n) {
                if (f == 0f) // Fix for negative zero case (Where value == -0.0, and 0.0 == -0.0)
                    f = 0f
                axis.entries[i] = f
                f += interval
                ++i
            }


        }

        if(interval < 1f){
            axis.decimals = (-log10(interval)).roundToInt()
        }else{
            axis.decimals = 0
        }


    }

    abstract fun renderAxisLabels(c: Canvas)

    abstract fun renderGridLines(c: Canvas)

    abstract fun renderAxisLine(c: Canvas)

    abstract fun renderLimitLines(c: Canvas)

}