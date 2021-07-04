package com.example.vitameanshospitaldoctor.customview

import android.content.Context
import android.util.AttributeSet
import com.example.vitameanshospitaldoctor.customview.components.YAxis
import com.example.vitameanshospitaldoctor.customview.render.BarChartRenderer
import com.example.vitameanshospitaldoctor.customview.utils.Transformer

class BarChart: Chart<BarData>, BarDataProvider {

    var highLightFullBarEnabled = false

    var drawValueAboveBar = true

    var drawBarShadow = false

    var fitBars = false

    var axisLeft = YAxis(YAxis.AxisDependency.LEFT)

    var axisRight = YAxis(YAxis.AxisDependency.RIGHT)

    var leftAxisTransformer = Transformer(viewPortHandler)

    var rightAxisTransformer = Transformer(viewPortHandler)

    var axisRendererLeft = YAxis



    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

    override fun init(){
        renderer = BarChartRenderer(this,viewPortHandler)
        xAxis.spaceMin = 0.5f
        xAxis.spaceMax = 0.5f
    }

    override fun notifyDataSetChanged() {
        TODO("Not yet implemented")
    }

    override fun getBarData(): BarData {
        TODO("Not yet implemented")
    }

    override fun isInverted(axis: YAxis.AxisDependency): Boolean {
        TODO("Not yet implemented")
    }

    override fun getTransformer(axis: YAxis.AxisDependency): Transformer {
        TODO("Not yet implemented")
    }

}