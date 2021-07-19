package com.example.vitameanshospitaldoctor.customview.render

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.vitameanshospitaldoctor.customview.Entry
import com.example.vitameanshospitaldoctor.customview.utils.ViewPortHandler
import com.example.vitameanshospitaldoctor.utils.Util

abstract class DataRenderer(viewPortHandler: ViewPortHandler) : Renderer(viewPortHandler) {

    lateinit var renderPaint: Paint

    lateinit var hightlightPaint: Paint

    lateinit var drawPaint: Paint

    lateinit var valuePaint: Paint

    init {

        renderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        renderPaint.style = Paint.Style.FILL

        drawPaint = Paint(Paint.DITHER_FLAG)

        valuePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        valuePaint.color = Color.rgb(63,63,63)
        valuePaint.textAlign = Paint.Align.CENTER
        valuePaint.textSize = Util.dpToPx(9f)

        hightlightPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        hightlightPaint.style = Paint.Style.STROKE
        hightlightPaint.strokeWidth = 2f
        hightlightPaint.color = Color.rgb(255,187,115)

    }

    abstract fun initBufferes()

    abstract fun drawData(c: Canvas)

    abstract fun drawValues(c: Canvas)

    fun drawValue(c: Canvas, value:Float, entry: Entry, dataSetIndex: Int, x: Float, y: Float, color: Int){
        valuePaint.color = color
        c.drawText("${value}", x, y, valuePaint)
    }


}