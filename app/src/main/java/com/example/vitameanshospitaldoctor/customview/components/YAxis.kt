package com.example.vitameanshospitaldoctor.customview.components

import android.graphics.Color
import android.graphics.Paint
import com.example.vitameanshospitaldoctor.customview.utils.Utils
import com.example.vitameanshospitaldoctor.utils.Util
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class YAxis(position: AxisDependency = AxisDependency.LEFT) : AxisBase() {

    var drawBottomYLabelEntry = true

    var drawTopYLabelEntry = true

    var inverted = false

    var drawZeroLine = false

    var useAutoScaleRestrictionMin = false

    var useAutoScaleRestrictionMax = false

    var zeroLineColor = Color.GRAY

    var zeroLineWidth = 1f

    var spacePercentTop = 10f

    var spacePercentBottom = 10f

    var position: YAxisLabelPosition = YAxisLabelPosition.OUTSIDE_CHART

    var xLabelOffset = 0f

    var axisDependency: AxisDependency = position

    var minWidth = 0f

    var maxWidth = Float.POSITIVE_INFINITY

    var limitLines = ArrayList<LimitLine>()


    init {
        yOffset = 0f
    }

    fun isDrawBottomYLabelEntryEnabled(): Boolean{
        return drawBottomYLabelEntry
    }

    fun isDrawTopYLabelEntryEnabled(): Boolean{
        return drawTopYLabelEntry
    }



    enum class AxisDependency{
        LEFT, RIGHT
    }

    enum class YAxisLabelPosition{
        OUTSIDE_CHART, INSIDE_CHART
    }

    override fun calculate(dataMin: Float, dataMax: Float) {

        var min = dataMin
        var max = dataMax

        var range = abs(max - min)

        if( range == 0f){
            max += 1f
            min -= 1f
        }

        range = abs(max - min)

        axisMinimum = min - (range / 100f) * spacePercentBottom
        axisMaximum = max + (range / 100f) * spacePercentTop

        axisRange = abs(axisMinimum - axisMaximum)
    }

    fun getRequiredWidthSpace(p: Paint): Float {
        p.textSize = textSize

        var label = getLongestLabel()
        var width = Utils.calcTextHeight(p, label) + xOffset * 2f

        var minWidth = minWidth
        var maxWidth = maxWidth

        if(minWidth > 0f) minWidth = Util.dpToPx(minWidth)
        if(maxWidth > 0f && maxWidth != Float.POSITIVE_INFINITY) maxWidth = Util.dpToPx(maxWidth)

        width = max(minWidth, min(width, if(maxWidth > 0.0) maxWidth else width))
        return width
    }


}