package com.example.vitameanshospitaldoctor.customview.components

import android.graphics.Color

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

    init {
        yOffset = 0f
    }

    fun isDrawBottomYLabelEntryEnabled(): Boolean{
        return drawBottomYLabelEntry
    }

    fun isDrawTopYLabelEntryEnabled(): Boolean{
        return drawTopYLabelEntry
    }

    fun getFormattedLabel(index: Int): String{

        return if(index<0 || index>= entries.size) ""
        else getValueFormatter().getFormattedValue(entries[index], this)
    }

    enum class AxisDependency{
        LEFT, RIGHT
    }

    enum class YAxisLabelPosition{
        OUTSIDE_CHART, INSIDE_CHART
    }
}