package com.example.vitameanshospitaldoctor.customview

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

    private fun notifyDataChanged() { calcMinMax() }

    private fun calcMinMax() {

        yMax = -Float.MAX_VALUE
        yMin = Float.MAX_VALUE
        xMax = -Float.MAX_VALUE
        xMin = Float.MAX_VALUE

        for(dataSet in datasets){
            calcMinMax(dataSet)
        }
    }

    private fun calcMinMax(dataSet: T) {

        if(yMax < dataSet.getYMax()) yMax = dataSet.getYMax()
        if(yMin > dataSet.getYMin()) yMin = dataSet.getYMin()
        if(xMax < dataSet.getXMax()) xMax = dataSet.getXMax()
        if(xMin > dataSet.getXMin()) xMin = dataSet.getXMin()
    }

    fun getDataSetByIndex(index: Int): T?{
        if(index < 0 || index >= datasets.size) return null
        return datasets[index]
    }

    fun getEntryCount(): Int{
        return datasets.size
    }

}