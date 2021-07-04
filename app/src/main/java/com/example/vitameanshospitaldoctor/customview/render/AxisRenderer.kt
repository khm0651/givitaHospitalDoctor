package com.example.vitameanshospitaldoctor.customview.render

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.vitameanshospitaldoctor.customview.components.AxisBase
import com.example.vitameanshospitaldoctor.customview.utils.Transformer
import com.example.vitameanshospitaldoctor.customview.utils.ViewPortHandler
import com.example.vitameanshospitaldoctor.utils.Util

abstract class AxisRenderer(
    var axis: AxisBase,
    var trans: Transformer,
    viewPortHandler: ViewPortHandler,
) : Renderer(viewPortHandler) {

    lateinit var gridPaint: Paint

    lateinit var axisLabelPaint: Paint

    lateinit var axisLinePaint: Paint

    lateinit var limitLinePaint: Paint

    init {

        axisLabelPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        axisLabelPaint.color = Color.BLACK
        axisLabelPaint.textSize = Util.dpToPx(10f)

        gridPaint = Paint()
        gridPaint.color = Color.GRAY
        gridPaint.strokeWidth = 1f
        gridPaint.style = Paint.Style.STROKE
        gridPaint.alpha = 90

        axisLinePaint = Paint()
        axisLinePaint.color = Color.BLACK
        axisLinePaint.strokeWidth = 1f
        axisLinePaint.style = Paint.Style.STROKE

        limitLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        limitLinePaint.style = Paint.Style.STROKE
    }

    abstract fun renderAxisLabels(c: Canvas)

    abstract fun renderGridLines(c: Canvas)

    abstract fun renderAxisLine(c: Canvas)

    abstract fun renderLimitLines(c: Canvas)

}