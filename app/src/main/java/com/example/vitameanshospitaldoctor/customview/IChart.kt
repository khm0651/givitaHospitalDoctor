package com.example.vitameanshospitaldoctor.customview

import android.graphics.RectF

interface IChart<T> {

    fun getXChartMin(): Float

    fun getXChartMax(): Float

    fun getXRange(): Float

    fun getYChartMin(): Float

    fun getYChartMax(): Float

    fun getMaxHighlightDistance()

    fun getWidth()

    fun getHeight()

    fun getContentRect(): RectF

    fun getMaxVisibleCount(): Int
}