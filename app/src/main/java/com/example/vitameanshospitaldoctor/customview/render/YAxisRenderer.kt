package com.example.vitameanshospitaldoctor.customview.render

import android.graphics.*
import com.example.vitameanshospitaldoctor.customview.components.AxisBase
import com.example.vitameanshospitaldoctor.customview.components.LimitLine
import com.example.vitameanshospitaldoctor.customview.components.YAxis
import com.example.vitameanshospitaldoctor.customview.utils.Transformer
import com.example.vitameanshospitaldoctor.customview.utils.Utils
import com.example.vitameanshospitaldoctor.customview.utils.ViewPortHandler
import com.example.vitameanshospitaldoctor.utils.Util
import kotlin.math.max

class YAxisRenderer(
    var yAxis: YAxis,
    trans: Transformer,
    viewPortHandler: ViewPortHandler
) : AxisRenderer(yAxis, trans, viewPortHandler) {

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
                axisLabelPaint.textAlign = Paint.Align.LEFT
                xPos = viewPortHandler.contentRect.right + xOffSet
            }else{
                axisLabelPaint.textAlign = Paint.Align.RIGHT
                xPos = viewPortHandler.contentRect.right - xOffSet
            }

        }

        drawYLabels(c,xPos,positions,yOffSet)
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

    var renderGridLinesPath = Path()
    override fun renderGridLines(c: Canvas) {

        var clipRestoreCount = c.save()
        c.clipRect(gridClippingRect)
        var positions = getTransformedPositions()

        gridPaint.color = yAxis.gridColor
        gridPaint.strokeWidth = yAxis.gridLineWidth
        gridPaint.pathEffect = yAxis.gridDashPathEffect

        var gridLinePath = renderGridLinesPath
        gridLinePath.reset()

        for(i in 0 until positions.size step 2){
            c.drawPath(linePath(gridLinePath, i, positions), gridPaint)
            gridLinePath.reset()
        }

        c.restoreToCount(clipRestoreCount)

    }

    private fun linePath(p: Path, i: Int, positions: FloatArray): Path {
        p.moveTo(viewPortHandler.contentRect.left, positions[i+1])
        p.lineTo(viewPortHandler.contentRect.right, positions[i+1])
        return p

    }

    var gridClippingRect = RectF()
        get() {
            gridClippingRect.set(viewPortHandler.contentRect)
            gridClippingRect.inset(0f, -axis.gridLineWidth)
            return gridClippingRect
        }



    override fun renderAxisLine(c: Canvas) {

        axisLinePaint.color = yAxis.axisLineColor
        axisLinePaint.strokeWidth = yAxis.axisLineWidth

        if(yAxis.axisDependency == YAxis.AxisDependency.LEFT){
            c.drawLine(viewPortHandler.contentRect.left, viewPortHandler.contentRect.top, viewPortHandler.contentRect.left, viewPortHandler.contentRect.bottom, axisLinePaint)
        }else{
            c.drawLine(viewPortHandler.contentRect.right, viewPortHandler.contentRect.top, viewPortHandler.contentRect.right, viewPortHandler.contentRect.bottom, axisLinePaint)
        }
    }


    var renderLimitLines = Path()
    var renderLimitLinesBuffer = FloatArray(2)
    var limitLineClippingRect = RectF()

    override fun renderLimitLines(c: Canvas) {

        var limitLines = yAxis.limitLines

        if(limitLines.size <= 0) return

        var pts = renderLimitLinesBuffer
        pts[0] = 0f
        pts[1] = 0f
        var limitLinePath = renderLimitLines
        limitLinePath.reset()

        for(i in 0 until limitLines.size){

            var l = limitLines[i]

            var clipRestoreCount = c.save()
            limitLineClippingRect.set(viewPortHandler.contentRect)
            limitLineClippingRect.inset(0f, -l.lineWidth)
            c.clipRect(limitLineClippingRect)

            limitLinePaint.style = Paint.Style.STROKE
            limitLinePaint.color = l.lineColor
            limitLinePaint.strokeWidth = l.lineWidth
            limitLinePaint.pathEffect = l.dashPathEffect

            pts[1] = l.limit

            trans.pointValuesToPixel(pts)

            limitLinePath.moveTo(viewPortHandler.contentRect.left, pts[1])
            limitLinePath.lineTo(viewPortHandler.contentRect.right, pts[1])

            c.drawPath(limitLinePath, limitLinePaint)
            limitLinePath.reset()

            var label = l.label

            if(label != ""){

                limitLinePaint.style = l.textStyle
                limitLinePaint.pathEffect = null
                limitLinePaint.color = l.textColor
                limitLinePaint.strokeWidth = 0.5f
                limitLinePaint.textSize = l.textSize

                var labelLineHeight = Utils.calcTextHeight(limitLinePaint, label)
                var xOffSet = Util.dpToPx(4f) + l.xOffSet
                var yOffSet = l.lineWidth + labelLineHeight + l.yOffSet

                var position = l.labelPosition

                when(position){

                    LimitLine.LimitLabelPosition.RIGHT_TOP -> {

                        limitLinePaint.textAlign = Paint.Align.RIGHT
                        c.drawText(label,
                            viewPortHandler.contentRect.right - xOffSet,
                            pts[1] - yOffSet + labelLineHeight, limitLinePaint)
                    }

                    LimitLine.LimitLabelPosition.RIGHT_BOTTOM -> {

                        limitLinePaint.textAlign = Paint.Align.RIGHT
                        c.drawText(label,
                            viewPortHandler.contentRect.right - xOffSet,
                            pts[1] + yOffSet, limitLinePaint)
                    }

                    LimitLine.LimitLabelPosition.LEFT_TOP -> {

                        limitLinePaint.textAlign = Paint.Align.LEFT
                        c.drawText(label,
                            viewPortHandler.contentRect.left + xOffSet,
                            pts[1] - yOffSet + labelLineHeight, limitLinePaint)
                    }

                    LimitLine.LimitLabelPosition.LEFT_BOTTOM -> {

                        limitLinePaint.textAlign = Paint.Align.LEFT
                        c.drawText(label,
                                viewPortHandler.contentRect.left + xOffSet,
                                pts[1] + yOffSet, limitLinePaint)
                    }
                }
            }

            c.restoreToCount(clipRestoreCount)
        }
    }


}