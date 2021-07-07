package com.example.vitameanshospitaldoctor.customview.components

import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import com.example.vitameanshospitaldoctor.utils.Util

class LimitLine(
    var limit: Float = 0f,
    var label: String = ""
){

    var lineWidth = 2f
        set(value) {
            var width = 0f
            if(value < 0.2f) width = 0.2f
            if(value > 12.0f) width = 12.0f
            field = Util.dpToPx(width)
        }

    var lineColor = Color.rgb(237,91,91)

    var textStyle = Paint.Style.FILL_AND_STROKE

    var dashPathEffect: DashPathEffect? = null

    var labelPosition = LimitLabelPosition.RIGHT_TOP

    var textColor = Color.BLACK

    var textSize = Util.dpToPx(10f)

    var xOffSet = 5f

    var yOffSet = 5f

    enum class LimitLabelPosition{
        LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM
    }

}