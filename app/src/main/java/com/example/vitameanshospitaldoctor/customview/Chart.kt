package com.example.vitameanshospitaldoctor.customview

import android.graphics.Paint
import android.graphics.RectF
import android.view.ViewGroup
import com.example.vitameanshospitaldoctor.customview.components.XAxis

class Chart<T: ChartData<out IDataSet<out Entry>>>: ViewGroup, IChart<T> {

    var logEnabled: Boolean = false

    var data: T? = null

    var highLightPerTapEnabled: Boolean = true

    var dragDecelerationEnabled: Boolean = true

    var dragDecelerationFrictionCoef: Float = 0.9f

    var descPaint: Paint = Paint()

    var infoPaint: Paint = Paint()

    var xAxis: XAxis = XAxis()

    var touchEnabled = true

    var noDataText = "차트 데이터가 없습니다"



    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }

    override fun getXChartMin(): Float {
        TODO("Not yet implemented")
    }

    override fun getXChartMax(): Float {
        TODO("Not yet implemented")
    }

    override fun getXRange(): Float {
        TODO("Not yet implemented")
    }

    override fun getYChartMin(): Float {
        TODO("Not yet implemented")
    }

    override fun getYChartMax(): Float {
        TODO("Not yet implemented")
    }

    override fun getMaxHighlightDistance() {
        TODO("Not yet implemented")
    }

    override fun getWidth() {
        TODO("Not yet implemented")
    }

    override fun getHeight() {
        TODO("Not yet implemented")
    }

    override fun getContentRect(): RectF {
        TODO("Not yet implemented")
    }


    override fun getMaxVisibleCount(): Int {
        TODO("Not yet implemented")
    }
}