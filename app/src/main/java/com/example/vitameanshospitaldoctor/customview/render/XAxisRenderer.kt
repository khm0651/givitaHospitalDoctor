package com.example.vitameanshospitaldoctor.customview.render

import android.graphics.*
import com.example.vitameanshospitaldoctor.customview.PointF
import com.example.vitameanshospitaldoctor.customview.components.LimitLine

import com.example.vitameanshospitaldoctor.customview.components.XAxis
import com.example.vitameanshospitaldoctor.customview.utils.Transformer
import com.example.vitameanshospitaldoctor.customview.utils.Utils
import com.example.vitameanshospitaldoctor.customview.utils.ViewPortHandler
import com.example.vitameanshospitaldoctor.utils.Util
import kotlin.math.round
import kotlin.math.roundToInt

class XAxisRenderer(
    var xAxis: XAxis,
    trans: Transformer,
    viewPortHandler: ViewPortHandler,
) : AxisRenderer(xAxis, trans, viewPortHandler) {

    init {

        axisLabelPaint.color = Color.BLACK
        axisLabelPaint.textAlign = Paint.Align.CENTER
        axisLabelPaint.textSize = Util.dpToPx(10f)
    }

    override fun computeAxis(min: Float, max: Float, inverted: Boolean) {

        var t_min = min
        var t_max = max

        if(viewPortHandler.contentWidth() > 10 && !viewPortHandler.isFullyZoomedOutY()){

            var p1 = trans.getValuesByTouchPoint(viewPortHandler.contentRect.left, viewPortHandler.contentRect.top)
            var p2 = trans.getValuesByTouchPoint(viewPortHandler.contentRect.left, viewPortHandler.contentRect.top)

            if(inverted){

                t_min = p2.x
                t_max = p1.x
            }else{

                t_min = p1.x
                t_max = p2.x
            }
        }

        computeAxisValues(t_min, t_max)
    }

    override fun computeAxisValues(min: Float, max: Float) {
        super.computeAxisValues(min, max)

        computeSize()
    }

    private fun computeSize() {

        var longest = xAxis.getLongestLabel()

        axisLabelPaint.textSize = xAxis.textSize

        var labelSize = Utils.calcTextSize(axisLabelPaint, longest)

        var labelWidth = labelSize.width
        var labelHeight = Utils.calcTextHeight(axisLabelPaint, "Q").toFloat()

        var labelRotatedSize = Utils.getSizeOfRotatedRectangleByDegrees(
            labelWidth,
            labelHeight,
            xAxis.labelRotationAngle
        )

        xAxis.labelWidth = labelWidth.roundToInt()
        xAxis.labelHeight = labelHeight.roundToInt()

        xAxis.labelRotatedWidth = labelRotatedSize.width.roundToInt()
        xAxis.labelRotatedHeight = labelRotatedSize.height.roundToInt()


    }

    override fun renderAxisLabels(c: Canvas) {

        var yOffSet = xAxis.yOffset

        axisLabelPaint.textSize = xAxis.textSize
        axisLabelPaint.color = xAxis.textColor

        var pointF = PointF(0f,0f)
        when(xAxis.position){

            XAxis.XAxisPostion.TOP -> {
                pointF.x = 0.5f
                pointF.y = 1.0f
                drawLabels(c, viewPortHandler.contentRect.top - yOffSet, pointF)
            }
        }
    }

    private fun drawLabels(c: Canvas, pos: Float, anchor: PointF) {

        var labelRotationAngleDegrees = xAxis.labelRotationAngle
        var positions = FloatArray(xAxis.entries.size * 2)

        for(i in 0 until positions.size step 2){
            positions[i] = xAxis.centeredEntries[i / 2]
        }

        trans.pointValuesToPixel(positions)

        for(i in 0 until positions.size step 2){

            var x = positions[i]

            if(viewPortHandler.isInBoundsX(x)){

                var label = xAxis.getValueFormatter().getFormattedValue(xAxis.entries[i / 2], axis)

                drawLabel(c, label, x, pos, anchor, labelRotationAngleDegrees )
            }
        }
    }

    private fun drawLabel(c: Canvas, label: String, x: Float, y: Float, anchor: PointF, labelRotationAngleDegrees: Float) {
        Utils.drawxAxisValue(c, label, x, y, axisLabelPaint,anchor, labelRotationAngleDegrees)
    }


    var renderGridLinesPath = Path()
    var renderGridlinesBuffer = FloatArray(2)
    var gridClippingRect = RectF()
        get() {
            gridClippingRect.set(viewPortHandler.contentRect)
            gridClippingRect.inset(-axis.gridLineWidth, 0f)
            return gridClippingRect
        }
    override fun renderGridLines(c: Canvas) {

        var clipRestoreCount = c.save()
        c.clipRect(gridClippingRect)

        if(renderGridlinesBuffer.size != axis.entries.size * 2){
            renderGridlinesBuffer = FloatArray(xAxis.entries.size * 2)
        }

        var positions = renderGridlinesBuffer

        for(i in 0 until positions.size step 2){
            positions[i] = xAxis.entries[i / 2]
            positions[i + 1] = xAxis.entries[i / 2]
        }

        trans.pointValuesToPixel(positions)

        setupGridPaint()

        var gridLinePath = renderGridLinesPath
        gridLinePath.reset()

        for(i in 0 until positions.size step 2){
            drawGridLine(c, positions[i], positions[i+1], gridLinePath)
        }

        c.restoreToCount(clipRestoreCount)
    }

    private fun drawGridLine(c: Canvas, x: Float, y: Float, gridLinePath: Path) {

        gridLinePath.moveTo(x, viewPortHandler.contentRect.bottom)
        gridLinePath.lineTo(x, viewPortHandler.contentRect.top)

        c.drawPath(gridLinePath, gridPaint)

        gridLinePath.reset()
    }

    private fun setupGridPaint() {
        gridPaint.color = xAxis.gridColor
        gridPaint.strokeWidth = xAxis.gridLineWidth
        gridPaint.pathEffect = xAxis.gridDashPathEffect
    }


    override fun renderAxisLine(c: Canvas) {

        axisLinePaint.color = xAxis.axisLineColor
        axisLinePaint.strokeWidth = xAxis.axisLineWidth
        axisLinePaint.pathEffect = xAxis.axisLineDashPathEffect

        when(xAxis.position){

            XAxis.XAxisPostion.TOP,XAxis.XAxisPostion.TOP_INSIDE,XAxis.XAxisPostion.BOTH_SIDED ->{
                c.drawLine(viewPortHandler.contentRect.left, viewPortHandler.contentRect.top, viewPortHandler.contentRect.right, viewPortHandler.contentRect.top, axisLinePaint)
            }

            XAxis.XAxisPostion.BOTTOM, XAxis.XAxisPostion.BOTTOM_INSIDE, XAxis.XAxisPostion.BOTH_SIDED ->{
                c.drawLine(viewPortHandler.contentRect.left, viewPortHandler.contentRect.bottom, viewPortHandler.contentRect.right, viewPortHandler.contentRect.bottom, axisLinePaint)
            }
        }
    }

    var renderLimitLinesBuffer = FloatArray(2)
    var limitLineClippingRect = RectF()
    override fun renderLimitLines(c: Canvas) {
        var limitLines = xAxis.limitLines

        if(limitLines.size <= 0) return

        var position = renderLimitLinesBuffer
        position[0] = 0f
        position[1] = 0f

        for(i in 0 until limitLines.size){

            var l = limitLines[i]

            var clipRestoreCount = c.save()
            limitLineClippingRect.set(viewPortHandler.contentRect)
            limitLineClippingRect.inset(-l.lineWidth, 0f)
            c.clipRect(limitLineClippingRect)

            position[0] = l.limit
            position[1] = 0f

            trans.pointValuesToPixel(position)

            renderLimitLine(c, l, position)
            renderLimitLineLabel(c, l, position, 2f + l.yOffSet)

            c.restoreToCount(clipRestoreCount)
        }
    }

    private fun renderLimitLineLabel(c: Canvas, limitLine: LimitLine, position: FloatArray, yOffSet: Float) {

        var label = limitLine.label

        if(label == "") return

        limitLinePaint.style = limitLine.textStyle
        limitLinePaint.pathEffect = null
        limitLinePaint.color = limitLine.textColor
        limitLinePaint.strokeWidth = 0.5f
        limitLinePaint.textSize = limitLine.textSize

        var xOffset = limitLine.lineWidth + limitLine.xOffSet

        var labelPosition = limitLine.labelPosition

        when(labelPosition){

            LimitLine.LimitLabelPosition.RIGHT_TOP -> {

                var labelLineHeight = Utils.calcTextHeight(limitLinePaint,label)
                limitLinePaint.textAlign = Paint.Align.LEFT
                c.drawText(label, position[0] + xOffset, viewPortHandler.contentRect.top + yOffSet + labelLineHeight, limitLinePaint)
            }

            LimitLine.LimitLabelPosition.RIGHT_BOTTOM -> {

                limitLinePaint.textAlign = Paint.Align.LEFT
                c.drawText(label, position[0] + xOffset, viewPortHandler.contentRect.bottom - yOffSet, limitLinePaint)
            }

            LimitLine.LimitLabelPosition.LEFT_TOP -> {

                limitLinePaint.textAlign = Paint.Align.RIGHT
                var labelLineHeight = Utils.calcTextHeight(limitLinePaint, label)
                c.drawText(label, position[0] - xOffset, viewPortHandler.contentRect.top + yOffSet + labelLineHeight,limitLinePaint)
            }

            LimitLine.LimitLabelPosition.LEFT_BOTTOM -> {

                limitLinePaint.textAlign = Paint.Align.RIGHT
                c.drawText(label, position[0] - xOffset, viewPortHandler.contentRect.bottom - yOffSet, limitLinePaint)
            }
        }
    }

    var limitLineSegmentsBuffer = FloatArray(4)
    var limitLinePath = Path()
    private fun renderLimitLine(c: Canvas, limitLine: LimitLine, position: FloatArray) {

        limitLineSegmentsBuffer[0] = position[0]
        limitLineSegmentsBuffer[1] = viewPortHandler.contentRect.top
        limitLineSegmentsBuffer[2] = position[0]
        limitLineSegmentsBuffer[3] = viewPortHandler.contentRect.bottom

        limitLinePath.reset()
        limitLinePath.moveTo(limitLineSegmentsBuffer[0], limitLineSegmentsBuffer[1])
        limitLinePath.lineTo(limitLineSegmentsBuffer[2], limitLineSegmentsBuffer[3])

        limitLinePaint.style = Paint.Style.STROKE
        limitLinePaint.color = limitLine.lineColor
        limitLinePaint.strokeWidth = limitLine.lineWidth
        limitLinePaint.pathEffect = limitLine.dashPathEffect

        c.drawPath(limitLinePath, limitLinePaint)
    }
}