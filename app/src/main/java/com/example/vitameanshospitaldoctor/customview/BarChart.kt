package com.example.vitameanshospitaldoctor.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.example.vitameanshospitaldoctor.customview.components.AxisBase
import com.example.vitameanshospitaldoctor.customview.components.XAxis
import com.example.vitameanshospitaldoctor.customview.components.YAxis
import com.example.vitameanshospitaldoctor.customview.render.BarChartRenderer
import com.example.vitameanshospitaldoctor.customview.render.XAxisRenderer
import com.example.vitameanshospitaldoctor.customview.render.YAxisRenderer
import com.example.vitameanshospitaldoctor.customview.utils.Transformer
import com.example.vitameanshospitaldoctor.utils.Util
import java.lang.Float.max
import java.util.*

class BarChart: Chart<BarData>, BarDataProvider {

    var highLightFullBarEnabled = false

    var drawValueAboveBar = true

    var drawBarShadow = false

    var fitBars = false

    lateinit var axisLeft: YAxis

    lateinit var axisRight: YAxis

    lateinit var leftAxisTransformer: Transformer

    lateinit var rightAxisTransformer: Transformer

    lateinit var axisRendererLeft: YAxisRenderer

    lateinit var axisRendererRight: YAxisRenderer

    lateinit var xAxisRenerer: XAxisRenderer

    var minOffset = 15f

    lateinit var gridBackgroundPaint: Paint

    lateinit var borderPaint: Paint

    var drawGridBackground = true

    var drawBorders = true

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

    override fun init(){
        super.init()
        gridBackgroundPaint = Paint()
        gridBackgroundPaint.style = Paint.Style.FILL
        gridBackgroundPaint.color = Color.rgb(240,240,240)

        borderPaint = Paint()
        borderPaint.style = Paint.Style.STROKE
        borderPaint.color = Color.BLACK
        borderPaint.strokeWidth = Util.dpToPx(1f)

        axisLeft = YAxis(YAxis.AxisDependency.LEFT)
        axisRight = YAxis(YAxis.AxisDependency.RIGHT)
        leftAxisTransformer = Transformer(viewPortHandler)

        rightAxisTransformer = Transformer(viewPortHandler)

        axisRendererLeft = YAxisRenderer(axisLeft, leftAxisTransformer, viewPortHandler)

        axisRendererRight = YAxisRenderer(axisRight, leftAxisTransformer, viewPortHandler)

        renderer = BarChartRenderer(this,viewPortHandler)

        xAxisRenerer = XAxisRenderer(xAxis,leftAxisTransformer,viewPortHandler)

        xAxis.spaceMin = 0.5f
        xAxis.spaceMax = 0.5f

        setXAxis()
        setYAxis()
        setData(10,30f)

    }

    private fun setYAxis() {
        axisLeft.labelCount = 8
        axisLeft.spacePercentTop = 15f
        axisLeft.position = YAxis.YAxisLabelPosition.OUTSIDE_CHART
        axisLeft.axisMinimum = 0f
        axisLeft.setValueFormatter(object : IAxisValueFormatter {
            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                return value.toString()
            }
        })

        axisRight.labelCount = 8
        axisRight.spacePercentTop = 15f
        axisRight.axisMinimum = 0f
        axisRight.setValueFormatter(object : IAxisValueFormatter {
            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                return value.toString()
            }
        })
    }

    private fun setXAxis() {
        xAxis.position = XAxis.XAxisPostion.BOTTOM
        xAxis.labelCount = 7
        xAxis.setValueFormatter(object : IAxisValueFormatter {
            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                return value.toString()
            }
        })

    }

    private fun setData(count: Int, range: Float) {
        val start = 1
        val values = ArrayList<BarEntry>()

        for(i in start until start + count){
            var v = (Math.random() * (range + 1)).toFloat()
            values.add(BarEntry(i.toFloat(), v))
        }

        val set1: BarDataSet
        if (data != null && data!!.datasets.isNotEmpty()) {
            set1 = data!!.getDataSetByIndex(0) as BarDataSet
            set1.entries = values
            data!!.notifyDataChanged()
            notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values, "The year 2017")
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.barWidth = 0.9f
            setChartData(data)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        axisRendererLeft.computeAxis(axisLeft.axisMinimum, axisLeft.axisMaximum, axisLeft.inverted)
        axisRendererRight.computeAxis(axisRight.axisMinimum, axisRight.axisMaximum, axisRight.inverted)
        xAxisRenerer.computeAxis(xAxis.axisMinimum, xAxis.axisMaximum, false)

        xAxisRenerer.renderAxisLine(canvas)
        axisRendererLeft.renderAxisLine(canvas)
        axisRendererRight.renderAxisLine(canvas)

        drawGridBackground(canvas)
        renderer.drawData(canvas)
    }

    fun drawGridBackground(c: Canvas) {
        if (drawGridBackground) {

            // draw the grid background
            c.drawRect(viewPortHandler.contentRect, gridBackgroundPaint)
        }
        if (drawBorders) {
            c.drawRect(viewPortHandler.contentRect, borderPaint)
        }
    }
    override fun notifyDataSetChanged() {
        data?.let {

            calMinMax(it)
            axisRendererLeft.computeAxis(axisLeft.axisMinimum,axisLeft.axisMaximum, axisLeft.inverted)
            axisRendererRight.computeAxis(axisRight.axisMinimum,axisRight.axisMaximum, axisRight.inverted)
            xAxisRenerer.computeAxis(xAxis.axisMinimum, xAxis.axisMaximum, false)

            calculateOffsets()
        }
    }

    private fun calMinMax(data: BarData) {
        xAxis.calculate(data.xMin , data.xMax)

        axisLeft.calculate(data.getYMin(YAxis.AxisDependency.LEFT),data.getYMax(YAxis.AxisDependency.LEFT))
        axisRight.calculate(data.getYMin(YAxis.AxisDependency.RIGHT),data.getYMax(YAxis.AxisDependency.RIGHT))

    }

    override fun getBarData(): BarData {
        return BarData()
    }

    override fun isInverted(axis: YAxis.AxisDependency): Boolean {
        return false
    }

    override fun getTransformer(axis: YAxis.AxisDependency): Transformer {
        return Transformer(viewPortHandler)
    }

    override fun calculateOffsets() {

        var offsetLeft = 0f
        var offsetRight = 0f
        var offsetTop = 0f
        var offsetBottom = 0f

        offsetLeft += axisLeft.getRequiredWidthSpace(axisRendererLeft.axisLabelPaint)
        offsetRight += axisRight.getRequiredWidthSpace(axisRendererRight.axisLabelPaint)

        var xLabelHeight = xAxis.labelRotatedHeight + xAxis.yOffset

        when(xAxis.position){

            XAxis.XAxisPostion.BOTTOM -> {
                offsetBottom += xLabelHeight
            }

            XAxis.XAxisPostion.TOP -> {
                offsetTop += xLabelHeight
            }

            XAxis.XAxisPostion.BOTH_SIDED -> {
                offsetBottom += xLabelHeight
                offsetTop += xLabelHeight
            }
        }

        var minOffset = Util.dpToPx(minOffset)

        viewPortHandler.restrainViewPort(
            max(minOffset, offsetLeft),
            max(minOffset, offsetTop),
            max(minOffset, offsetRight),
            max(minOffset, offsetBottom)
        )

        prepareOffsetMatrix()
        prepareValuePxMatrix()
    }

    private fun prepareValuePxMatrix() {
        rightAxisTransformer.prepareMatrixValuePx(
            xAxis.axisMinimum,
            xAxis.axisRange,
            axisRight.axisRange,
            axisRight.axisMinimum
        )
        leftAxisTransformer.prepareMatrixValuePx(
            xAxis.axisMinimum,
            xAxis.axisRange,
            axisLeft.axisRange,
            axisLeft.axisMinimum
        )
    }

    private fun prepareOffsetMatrix() {
        rightAxisTransformer.prepareMatrixOffset(axisRight.inverted)
        leftAxisTransformer.prepareMatrixOffset(axisLeft.inverted)

    }

}