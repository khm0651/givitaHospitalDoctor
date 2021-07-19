package com.example.vitameanshospitaldoctor.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.ViewGroup
import com.example.vitameanshospitaldoctor.customview.components.XAxis
import com.example.vitameanshospitaldoctor.customview.formatter.DefaultValueFormatter
import com.example.vitameanshospitaldoctor.customview.render.DataRenderer
import com.example.vitameanshospitaldoctor.customview.utils.ColorTemplate
import com.example.vitameanshospitaldoctor.customview.utils.ViewPortHandler
import com.example.vitameanshospitaldoctor.utils.Util
import java.util.*
import kotlin.math.abs
import kotlin.math.max

abstract class Chart<T: ChartData<out IDataSet<out Entry>>>: ViewGroup, IChart {

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

    lateinit var renderer: DataRenderer

    var viewPortHandler = ViewPortHandler()

    var extraTopOffset = 0f

    var extraRightOffset = 0f

    var extraBottomOffset = 0f

    var extraLeftOffset = 0f

    var maxHighlightDistance = 0f

    var offSetCalculated = false

    var defaultValueFormatter = DefaultValueFormatter(0)

    constructor(context: Context): super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle){
        init()
    }

    open fun init(){
        /**
             * ViewGroup을 상속하여 onDraw()를 override하면,
            invalidate()시에 onDraw()가 호출되지 않는다.

            왜, 그럴까?

            ViewGroup은 뭔가를 그리는 기능이 아니라, child view를 관리하는 객체이기 때문이다.
            그래서, 그려질 필요가 있을때는 dispatchDraw (Canvas canvas)가 호출된다.

            그래도 그리고 싶을때는 어떻게 해야 할 까?
            setWillNotDraw(false)를 추가하면 onDraw()가 호출되고 해당 부분을 그리면 된다.

            왜, 이렇게 했을까?

            setWillNotDraw()는 최적화를 위한 메쏘드다. ViewGroup과 같이 관리만 하는 것은
            draw 부분을 skip하려는 것이다.

         */
        setWillNotDraw(false)

        Util.init(context)
        maxHighlightDistance = Util.dpToPx(500f)

        xAxis = XAxis()

        infoPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        infoPaint.color = Color.rgb(247,189,51)
        infoPaint.textAlign = Paint.Align.CENTER
        infoPaint.textSize = Util.dpToPx(12f)
    }

//    fun initWithDummyData() {
//        val template = ColorTemplate()
//
//        val xVals = ArrayList<String>()
//        val calendar: Calendar = Calendar.getInstance()
//        for (i in 0..11) {
//            xVals.add(calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT,
//                Locale.getDefault()))
//        }
//        val dataSets: ArrayList<BarDataSet> = ArrayList()
//        for (i in 0..2) {
//            val yVals = ArrayList<BarEntry>()
//            for (j in 0..11) {
//                val `val` = (Math.random() * 100).toFloat()
//                yVals.add(BarEntry(`val`, j.toFloat()))
//            }
//            val set = BarDataSet(yVals.toList(), "DataSet $i")
//            dataSets.add(set) // add the datasets
//        }
//        // create a data object with the datasets
//        val data = BarData(dataSets)
//        setChartData(data)
//        invalidate()
//    }

    fun setChartData(data: T) {
        this.data = data
        offSetCalculated = false

        this.data?.let{ data ->
            setupDefaultFormatter(data.yMin,data.yMax)

            notifyDataSetChanged()
        }

    }

    abstract fun notifyDataSetChanged()

    abstract fun calculateOffsets()

    private fun setupDefaultFormatter(min: Float, max: Float) {

        var reference = 0f

        reference = if(data == null || data!!.datasets.size < 2) max(abs(min), abs(max)) else abs(max - min)

        var digits = Util.getDecimals(reference)

        defaultValueFormatter.setup(digits)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        viewPortHandler.chartDimens(w.toFloat(),h.toFloat())
        notifyDataSetChanged()
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for(i in 0 until childCount){
            getChildAt(i).layout(l,t,r,b)
        }
    }

    override fun getXChartMin(): Float {
        return 0f
    }

    override fun getXChartMax(): Float {
        return 0f
    }

    override fun getXRange(): Float {
        return 0f
    }

    override fun getYChartMin(): Float {
        return 0f
    }

    override fun getYChartMax(): Float {
        return 0f
    }

    override fun getMaxHighlightDistance() {

    }

    override fun getContentRect(): RectF {
        return RectF()
    }





}