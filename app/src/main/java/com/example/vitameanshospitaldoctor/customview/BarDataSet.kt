package com.example.vitameanshospitaldoctor.customview

import android.graphics.Color
import com.example.vitameanshospitaldoctor.customview.components.YAxis

class BarDataSet(
    entries: List<BarEntry> = arrayListOf(),
    label: String = "DataSet"
): DataSet<BarEntry>(entries, label), IBarDataSet {

    var highLightColor = Color.rgb(255,187,115)
    override var isVisible = true
    override var barBorderColor = Color.BLACK
    override var barBorderWidth = 0f
    override var highLightAlpha: Int = 120
    override var barShadowColor: Int = Color.BLACK

    override fun calcMinMax(e: BarEntry) {

        if(!e.y.isNaN()){

            if(e.y < yMin) yMin = e.y

            if(e.y > yMax) yMax = e.y

            calcMinMaxX(e)
        }
    }

}