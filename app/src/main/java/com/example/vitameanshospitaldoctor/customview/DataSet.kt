package com.example.vitameanshospitaldoctor.customview

import android.graphics.Color
import com.example.vitameanshospitaldoctor.customview.components.YAxis

abstract class DataSet<T: Entry>(
    entries:List<T>,
    var label:String = "DataSet"
):IDataSet<T> {

    var entries: List<T> = entries
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    protected var colors = arrayListOf<Int>(
        Color.rgb(140,234,255)
    )

    protected var valueColors = arrayListOf<Int>(
        Color.BLACK
    )

    override var axisDependency = YAxis.AxisDependency.LEFT

    protected var highlightEnabled = true

    override var yMax = -Float.MAX_VALUE

    override var yMin = Float.MAX_VALUE

    override var xMax = -Float.MAX_VALUE

    override var xMin = Float.MAX_VALUE

    init {
        calcMinMax()
    }

    open fun calcMinMax(e: T){

        calcMinMaxX(e)

        calcMinMaxY(e)
    }

    protected fun calcMinMaxX(e: T) {

        if(e.x < xMin) xMin = e.x

        if(e.x > xMax) xMax = e.x
    }

    protected fun calcMinMaxY(e: T){

        if(e.y < yMin) yMin = e.y

        if(e.y > yMax) yMax = e.y
    }


    fun notifyDataSetChanged(){
        calcMinMax()
    }

    override fun getEntryCount(): Int {
        return 0
    }

    override fun calcMinMax() {

        yMax = -Float.MAX_VALUE
        yMin = Float.MAX_VALUE
        xMax = -Float.MAX_VALUE
        xMin = Float.MAX_VALUE

        for(e in entries) {
            calcMinMax(e)
        }
    }

    override fun calcMinMaxY(fromX: Float, toX: Float) {
    }
}