package com.example.vitameanshospitaldoctor.customview

import android.graphics.Color
import com.example.vitameanshospitaldoctor.customview.components.YAxis

class BarDataSet(
    entries: List<BarEntry> = arrayListOf(),
    label: String = "DataSet"
): DataSet<BarEntry>(entries, label), IBarDataSet {

    var highLightColor = Color.rgb(255,187,115)

    override fun getBarShadowColor(): Int {
        TODO("Not yet implemented")
    }

    override fun getBarBorderWidth(): Float {
        TODO("Not yet implemented")
    }

    override fun getBarBorderColor(): Int {
        TODO("Not yet implemented")
    }

    override fun getHighLightAlpha(): Int {
        TODO("Not yet implemented")
    }

    override fun getAxisDependency(): YAxis.AxisDependency {
        TODO("Not yet implemented")
    }


    override fun isVisible(): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T> getEntryForIndex(index: Int): T {
        TODO("Not yet implemented")
    }


}