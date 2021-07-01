package com.example.vitameanshospitaldoctor.customview

import com.example.vitameanshospitaldoctor.customview.components.YAxis
import com.example.vitameanshospitaldoctor.customview.utils.Transformer

interface BarDataProvider: IChart {

    fun getBarData(): BarData

    fun isInverted(axis: YAxis.AxisDependency): Boolean

    fun getTransformer(axis: YAxis.AxisDependency): Transformer
}