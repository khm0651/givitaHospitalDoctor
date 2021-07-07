package com.example.vitameanshospitaldoctor.customview

import com.example.vitameanshospitaldoctor.customview.components.YAxis

interface IDataSet<T: Entry> {

    var yMin: Float

    var yMax: Float

    var xMin: Float

    var xMax: Float

    var axisDependency: YAxis.AxisDependency

    fun getEntryCount(): Int

    /**
     * mXMin, mXMax, mYMin, mYMax 중에서 최대 최소 값 계산
     */
    fun calcMinMax()

    fun calcMinMaxY(fromX: Float, toX: Float)

    fun isVisible(): Boolean

    fun <T> getEntryForIndex(index: Int): T
}