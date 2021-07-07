package com.example.vitameanshospitaldoctor.customview

import com.example.vitameanshospitaldoctor.customview.components.YAxis

abstract class ChartData<T: IDataSet<out Entry>>{

    var yMax: Float = -Float.MAX_VALUE
    var yMin: Float = Float.MAX_VALUE
    var xMax: Float = -Float.MAX_VALUE
    var xMin: Float = Float.MAX_VALUE
    var leftAxisMax: Float = -Float.MAX_VALUE
    var leftAxisMin: Float = Float.MAX_VALUE
    var rightAxisMax: Float = -Float.MAX_VALUE
    var rightAxisMin: Float = Float.MAX_VALUE
    var datasets: List<T> = arrayListOf()

    constructor(vararg dataSets: T) {
        datasets = arrayToList(dataSets)
        notifyDataChanged()
    }

    constructor(dataSets: List<T>) {
        datasets = dataSets
        notifyDataChanged()

    }
    private fun arrayToList(dataSets: Array<out T>): List<T> {

        var list = arrayListOf<T>()

        for(set in dataSets) list.add(set)

        return list
    }

    fun getYMin(axis: YAxis.AxisDependency): Float{
        return if (axis == YAxis.AxisDependency.LEFT) {
            if (leftAxisMin == Float.MAX_VALUE) {
                rightAxisMin
            } else {
                leftAxisMin
            }
        } else {
            if (rightAxisMin == Float.MAX_VALUE) {
                leftAxisMin
            } else {
                rightAxisMin
            }
        }
    }

    fun getYMax(axis: YAxis.AxisDependency): Float{
        return if (axis == YAxis.AxisDependency.LEFT) {
            if (leftAxisMax == -Float.MAX_VALUE) {
                rightAxisMax
            } else {
                leftAxisMax
            }
        } else {
            if (rightAxisMax == -Float.MAX_VALUE) {
                leftAxisMax
            } else{
                rightAxisMax
            }
        }
    }

    fun notifyDataChanged() { calcMinMax() }

    private fun calcMinMax() {

        yMax = -Float.MAX_VALUE
        yMin = Float.MAX_VALUE
        xMax = -Float.MAX_VALUE
        xMin = Float.MAX_VALUE

        for(dataSet in datasets){
            calcMinMax(dataSet)
        }

        leftAxisMax = -Float.MAX_VALUE
        leftAxisMin = Float.MAX_VALUE
        rightAxisMax = -Float.MAX_VALUE
        rightAxisMin = Float.MAX_VALUE

        var firstLeft: T? = getFirstLeft(datasets)

        firstLeft?.let {

            leftAxisMax = firstLeft.yMax
            leftAxisMin = firstLeft.yMin

            for(data in datasets){
                if(data.axisDependency == YAxis.AxisDependency.LEFT){
                    if(data.yMin < leftAxisMin) leftAxisMin = data.yMin
                    if(data.yMax > leftAxisMax) leftAxisMax = data.yMax
                }
            }
        }

        var firstRight = getFirstRight(datasets)

        firstRight?.let {

            rightAxisMax = firstRight.yMax
            rightAxisMin = firstRight.yMin

            for(data in datasets){
                if(data.axisDependency == YAxis.AxisDependency.RIGHT){
                    if(data.yMin < rightAxisMin) rightAxisMin = data.yMin
                    if(data.yMax > rightAxisMax) rightAxisMax = data.yMax
                }
            }
        }
    }

    private fun getFirstRight(datasets: List<T>): T? {
        for(data in datasets){
            if(data.axisDependency == YAxis.AxisDependency.RIGHT)
                return data
        }
        return null
    }

    private fun getFirstLeft(datasets: List<T>): T? {
        for(data in datasets){
            if(data.axisDependency == YAxis.AxisDependency.LEFT)
                return data
        }
        return null
    }

    private fun calcMinMax(dataSet: T) {

        if(yMax < dataSet.yMax) yMax = dataSet.yMax
        if(yMin > dataSet.yMin) yMin = dataSet.yMin
        if(xMax < dataSet.xMax) xMax = dataSet.xMax
        if(xMin > dataSet.xMin) xMin = dataSet.xMin

        if(dataSet.axisDependency == YAxis.AxisDependency.LEFT){
            if(leftAxisMax < dataSet.yMax) leftAxisMax = dataSet.yMax
            if(leftAxisMin > dataSet.yMin) leftAxisMin = dataSet.yMin
        }else{
            if(rightAxisMax < dataSet.yMax) rightAxisMax = dataSet.yMax
            if(rightAxisMin > dataSet.yMin) rightAxisMin = dataSet.yMin
        }
    }

    fun getDataSetByIndex(index: Int): T?{
        if(index < 0 || index >= datasets.size) return null
        return datasets[index]
    }

    fun getEntryCount(): Int{
        return datasets.size
    }

}