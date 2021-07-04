package com.example.vitameanshospitaldoctor.customview.formatter

import java.text.DecimalFormat
import kotlin.properties.Delegates

class DefaultValueFormatter(
    digits: Int
) {

    lateinit var format: DecimalFormat

    var decimalDigits by Delegates.notNull<Int>()

    init {
        setup(digits)
    }

    fun setup(digits: Int) {

        decimalDigits = digits

        val b = StringBuffer()
        for(i in 0 until digits){
            if(i==0) b.append(".")
            b.append("0")
        }

        format = DecimalFormat("###,###,###,##0$b")
    }

    fun getFormattedValue(value: Float): String{
        return format.format(value)
    }
}