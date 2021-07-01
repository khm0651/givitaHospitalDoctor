package com.example.vitameanshospitaldoctor.customview.buffer

import com.example.vitameanshospitaldoctor.customview.BarEntry
import com.example.vitameanshospitaldoctor.customview.IBarDataSet

class BarBuffer(
    var dataSetCount: Int,
    size: Int
) : AbstractBuffer<IBarDataSet>(size) {

    var dataSetIndex = 0

    var inverted = false

    var barWidth = 1f

    fun addBar(left: Float, top: Float, right: Float, bottom: Float){

        buffer[index++] = left
        buffer[index++] = top
        buffer[index++] = right
        buffer[index++] = bottom
    }

    fun feed(data: IBarDataSet){

        var size = data.getEntryCount()
        var barWidthHalf = barWidth / 2f

        for(i in 0 until size){

            val e: BarEntry = data.getEntryForIndex(i)

            val x = e.x
            val y = e.y

            var left = x - barWidthHalf
            var right = x + barWidthHalf
            var bottom: Float
            var top: Float

            if(inverted){
                bottom = if( y >= 0 ) y else 0f
                top = if( y <=0 ) y else 0f
            }else{
                top = if( y >= 0 ) y else 0f
                bottom = if( y <= 0) y else 0f
            }
            addBar(left, top, right, bottom)
        }

        reset()
    }

}