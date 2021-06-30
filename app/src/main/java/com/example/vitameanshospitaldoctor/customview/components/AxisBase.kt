package com.example.vitameanshospitaldoctor.customview.components

import android.graphics.Color

abstract class AxisBase {

    var gridColor = Color.GRAY

    var gridLineWidth = 1f

    var axisLineColor = Color.GRAY

    var axisLineWidth = 1f

    var entries = arrayListOf<Float>()

    var centeredEntries = arrayListOf<Float>()

    var yOffset = 5f

    var xOffset = 5f


}