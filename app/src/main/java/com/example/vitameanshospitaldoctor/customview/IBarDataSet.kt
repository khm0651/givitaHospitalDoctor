package com.example.vitameanshospitaldoctor.customview

import com.example.vitameanshospitaldoctor.customview.components.YAxis

interface IBarDataSet: IDataSet<BarEntry>  {

    fun getBarShadowColor(): Int

    fun getBarBorderWidth(): Float

    fun getBarBorderColor(): Int

    fun getHighLightAlpha(): Int

    fun getAxisDependency(): YAxis.AxisDependency




}