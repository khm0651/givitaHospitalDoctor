package com.example.vitameanshospitaldoctor.customview.buffer

abstract class AbstractBuffer<T>(
    size: Int
) {

    var index = 0

    var buffer: FloatArray = FloatArray(size){0f}

    var phaseX = 1f

    var phaseY = 1f

    var from = 0

    var to = 0

    fun reset() { index = 0 }

    fun size(): Int{
        return buffer.size
    }
}