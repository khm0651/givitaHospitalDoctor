package com.example.vitameanshospitaldoctor.customview.components

import android.graphics.Color
import android.graphics.DashPathEffect
import com.example.vitameanshospitaldoctor.customview.IAxisValueFormatter
import com.example.vitameanshospitaldoctor.utils.Util
import kotlin.math.abs

abstract class AxisBase {

    var gridColor = Color.GRAY

    var gridLineWidth = 1f

    var axisLineColor = Color.GRAY

    var axisLineWidth = 1f

    var entries = floatArrayOf()

    var centeredEntries = floatArrayOf()

    var decimals: Int = 0

    var entryCount = 0

    var yOffset = 5f

    var xOffset = 5f

    var gridDashPathEffect: DashPathEffect? = null

    var textSize = Util.dpToPx(10f)

    var textColor = Color.BLACK

    var axisLineDashPathEffect: DashPathEffect? = null

    lateinit var axisValueFormatter: IAxisValueFormatter

    var labelCount = 6

    var centerAxisLabels = false
    var isCenterAxisLabelsEnabled: Boolean = false
        get() { return centerAxisLabels && entryCount > 0}

    var forceLabels = false

    var axisMaximum = 0f

    var axisMinimum = 0f

    var spaceMin = 0f

    var spaceMax = 0f

    var axisRange = 0f

    fun setValueFormatter(f: IAxisValueFormatter){
        axisValueFormatter = f
    }
    fun getValueFormatter(): IAxisValueFormatter{
        return axisValueFormatter
    }

    open fun calculate(dataMin: Float, dataMax: Float){

        var min = dataMin - spaceMin

        var max = dataMax + spaceMax

        var range = abs(max - min)

        if(range == 0f){
            max += 1f
            min -= 1f
        }

        axisMinimum = min
        axisMaximum = max

        axisRange = abs(max - min)
    }


    fun getLongestLabel(): String {
        var longest = ""

        for(i in 0 until entries.size){
            var text = getFormattedLabel(i)

            if( longest.length < text.length) longest = text
        }

        return longest
    }

    fun getFormattedLabel(index: Int): String{

        return if(index<0 || index>= entries.size) ""
        else getValueFormatter().getFormattedValue(entries[index], this)
    }
}