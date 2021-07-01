package com.example.vitameanshospitaldoctor.customview

import android.graphics.RectF

interface IChart {

    fun getXChartMin(): Float

    fun getXChartMax(): Float

    fun getXRange(): Float

    fun getYChartMin(): Float

    fun getYChartMax(): Float

    fun getMaxHighlightDistance()

    fun getContentRect(): RectF

    fun getMaxVisibleCount(): Int

    fun <T> getData(): T
}