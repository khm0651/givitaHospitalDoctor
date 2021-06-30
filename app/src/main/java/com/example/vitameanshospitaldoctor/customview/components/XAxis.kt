package com.example.vitameanshospitaldoctor.customview.components

import com.example.vitameanshospitaldoctor.utils.Util

class XAxis() : AxisBase() {

    var labelWidth = 1

    var labelHeight = 1

    var labelRotatedWidth = 1

    var labelRotatedHeight = 1

    var labelRotationAngle = 0f

    var avoidFirstLastClipping = false

    var position: XAxisPostion = XAxisPostion.TOP

    init {
        yOffset = Util.dpToPx(4f)
    }

    enum class XAxisPostion{
        TOP, BOTTOM, BOTH_SIDED, TOP_INSIDE, BOTTOM_INSIDE
    }
}
