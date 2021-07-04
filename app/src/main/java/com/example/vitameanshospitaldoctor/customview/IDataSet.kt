package com.example.vitameanshospitaldoctor.customview

interface IDataSet<T: Entry> {

    fun getYMin(): Float

    fun getYMax(): Float

    fun getXMin(): Float

    fun getXMax(): Float

    fun getEntryCount(): Int

    /**
     * mXMin, mXMax, mYMin, mYMax 중에서 최대 최소 값 계산
     */
    fun calcMinMax()

    fun calcMinMaxY(fromX: Float, toX: Float)

    fun isVisible(): Boolean

    fun <T> getEntryForIndex(index: Int): T
}