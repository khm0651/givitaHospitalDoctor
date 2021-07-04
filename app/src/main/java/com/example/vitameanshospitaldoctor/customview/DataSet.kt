package com.example.vitameanshospitaldoctor.customview

import android.graphics.Color

abstract class DataSet<T: Entry>(
    var entries: List<T> = arrayListOf(),
    var label:String = "DataSet"
):IDataSet<T> {

    protected var colors = arrayListOf<Int>(
        Color.rgb(140,234,255)
    )

    protected var valueColors = arrayListOf<Int>(
        Color.BLACK
    )

    protected var axisDependency = null

    protected var highlightEnabled = true

    override fun getYMin(): Float {
        TODO("Not yet implemented")
    }

    override fun getYMax(): Float {
        TODO("Not yet implemented")
    }

    override fun getXMin(): Float {
        TODO("Not yet implemented")
    }

    override fun getXMax(): Float {
        TODO("Not yet implemented")
    }

    override fun getEntryCount(): Int {
        TODO("Not yet implemented")
    }

    override fun calcMinMax() {
        TODO("Not yet implemented")
    }

    override fun calcMinMaxY(fromX: Float, toX: Float) {
        TODO("Not yet implemented")
    }
}