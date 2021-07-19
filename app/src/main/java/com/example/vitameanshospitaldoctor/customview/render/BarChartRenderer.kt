package com.example.vitameanshospitaldoctor.customview.render

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.example.vitameanshospitaldoctor.customview.BarDataProvider
import com.example.vitameanshospitaldoctor.customview.IBarDataSet
import com.example.vitameanshospitaldoctor.customview.buffer.BarBuffer
import com.example.vitameanshospitaldoctor.customview.utils.ViewPortHandler
import com.example.vitameanshospitaldoctor.utils.Util
import java.nio.Buffer

class BarChartRenderer(
    val chart: BarDataProvider,
    viewPortHandler: ViewPortHandler,
) : DataRenderer(viewPortHandler) {

    var barRect = RectF()

    lateinit var barBuffers: Array<BarBuffer>

    var shadowPaint = Paint()

    var barBorderPaint = Paint()

    init {
        hightlightPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        hightlightPaint.style = Paint.Style.FILL
        hightlightPaint.color = Color.rgb(0,0,0)
        hightlightPaint.alpha = 120

        shadowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        shadowPaint.style = Paint.Style.FILL

        barBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        barBorderPaint.style = Paint.Style.STROKE
    }

    override fun initBufferes() {
        val bardata = chart.getBarData()
        barBuffers = Array(bardata.getDataSetCount()){
            var set = bardata.getDataSetByIndex(it)!!
            BarBuffer(set.getEntryCount() * 4,bardata.getDataSetCount())
        }

    }

    override fun drawData(c: Canvas) {
        val barData = chart.getBarData()

        for(i in barData.datasets.indices){
            val set = barData.getDataSetByIndex(i)
            set?.let {
                if(set.isVisible){
                    drawDataSet(c,set,i)
                }
            }

        }


    }

    fun drawDataSet(c: Canvas, dataSet: IBarDataSet, index: Int){
        val trans = chart.getTransformer(dataSet.axisDependency)

        barBorderPaint.color = dataSet.barBorderColor
        barBorderPaint.strokeWidth = Util.dpToPx(dataSet.barBorderWidth)

        var drawBorder = dataSet.barBorderWidth > 0f

        var buffer = barBuffers[index]
        buffer.dataSetIndex = index
        buffer.inverted = chart.isInverted(dataSet.axisDependency)
        buffer.barWidth = chart.getBarData().barWidth

        buffer.feed(dataSet)

        trans.pointValuesToPixel(buffer.buffer)


        var j = 0
        var pos = 0
        while (j < buffer.size()) {
            if (!viewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) {
                j += 4
                pos++
                continue
            }
            if (!viewPortHandler.isInBoundsRight(buffer.buffer[j])) break

            c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2],
                buffer.buffer[j + 3], renderPaint)


            if (drawBorder) {
                c.drawRect(buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2],
                    buffer.buffer[j + 3], barBorderPaint)
            }
            j += 4
            pos++
        }

    }

    override fun drawValues(c: Canvas) {
        TODO("Not yet implemented")
    }
}