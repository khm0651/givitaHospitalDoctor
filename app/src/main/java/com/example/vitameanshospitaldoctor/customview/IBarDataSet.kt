package com.example.vitameanshospitaldoctor.customview

import com.example.vitameanshospitaldoctor.customview.components.YAxis

interface IBarDataSet: IDataSet<BarEntry>  {

    var barBorderColor: Int

    var barBorderWidth: Float

    var barShadowColor: Int

    var highLightAlpha: Int

}