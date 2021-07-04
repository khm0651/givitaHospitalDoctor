package com.example.vitameanshospitaldoctor.customview.render

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.vitameanshospitaldoctor.customview.components.AxisBase
import com.example.vitameanshospitaldoctor.customview.components.YAxis
import com.example.vitameanshospitaldoctor.customview.utils.Transformer
import com.example.vitameanshospitaldoctor.customview.utils.Utils
import com.example.vitameanshospitaldoctor.customview.utils.ViewPortHandler
import kotlin.math.max

class YAxisRenderer(
    var yAxis: YAxis,
    axis: AxisBase,
    trans: Transformer,
    viewPortHandler: ViewPortHandler
) : AxisRenderer(axis, trans, viewPortHandler) {

    var zeroLinePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var getTransformedPositionsBuffer = FloatArray(2)

    init {

        zeroLinePaint.color = Color.GRAY
        zeroLinePaint.strokeWidth = 1f
        zeroLinePaint.style = Paint.Style.STROKE

    }

    fun getTransformedPositions(): FloatArray {

        if(getTransformedPositionsBuffer.size != yAxis.entries.size * 2){
            getTransformedPositionsBuffer = FloatArray(yAxis.entries.size * 2)
        }
        var positions = getTransformedPositionsBuffer

        for(i in 0 until positions.size step 2) positions[i+1] = yAxis.entries[i / 2]

        trans.pointValuesToPixel(positions)
        return positions

    }

    override fun renderAxisLabels(c: Canvas) {

        var positions = getTransformedPositions()

        var xOffSet = yAxis.xOffset
        var yOffSet = Utils.calcTextHeight(axisLabelPaint, "A") / 2.5f + yAxis.yOffset

        var dependency = yAxis.axisDependency
        var labelPosition = yAxis.position

        var xPos = 0f

        if( dependency == YAxis.AxisDependency.LEFT){

            if(labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART){
                axisLabelPaint.textAlign = Paint.Align.RIGHT
                xPos = viewPortHandler.contentRect.left - xOffSet
            }else{
                axisLabelPaint.textAlign = Paint.Align.LEFT
                xPos = viewPortHandler.contentRect.left + xOffSet
            }

        }else{

            if(labelPosition == YAxis.YAxisLabelPosition.OUTSIDE_CHART){
                axisLabelPaint.textAlign = Paint.Align.RIGHT
                xPos = viewPortHandler.contentRect.right - xOffSet
            }else{
                axisLabelPaint.textAlign = Paint.Align.LEFT
                xPos = viewPortHandler.contentRect.right + xOffSet
            }

        }


    }

    fun drawYLabels(c: Canvas, fixedPosition: Float, positions: FloatArray, offset: Float){

        val from = if(yAxis.isDrawBottomYLabelEntryEnabled()) 0 else 1
        val to = if(yAxis.isDrawTopYLabelEntryEnabled()) yAxis.entries.size else yAxis.entries.size -1

        var xOffSet = yAxis.xLabelOffset

        for(i in from until to){

            var text = yAxis.getFormattedLabel(i)

            c.drawText(text,
                fixedPosition + xOffSet,
                positions[i * 2 + 1] + offset,
                axisLabelPaint
            )

        }
    }

    override fun renderGridLines(c: Canvas) {
        TODO("Not yet implemented")
    }

    override fun renderAxisLine(c: Canvas) {
        TODO("Not yet implemented")
    }

    override fun renderLimitLines(c: Canvas) {
        TODO("Not yet implemented")
    }


}