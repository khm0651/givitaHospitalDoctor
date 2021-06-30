package com.example.vitameanshospitaldoctor.customview

interface IBarDataSet: IDataSet<BarEntry>  {

    fun getBarShadowColor(): Int

    fun getBarBorderWidth(): Float

    fun getBarBorderColor(): Int

    fun getHighLightAlpha(): Int


}