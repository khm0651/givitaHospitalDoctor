package com.example.vitameanshospitaldoctor.customview

import com.example.vitameanshospitaldoctor.customview.components.AxisBase

interface IAxisValueFormatter {

    fun getFormattedValue(value: Float, axis: AxisBase): String
}