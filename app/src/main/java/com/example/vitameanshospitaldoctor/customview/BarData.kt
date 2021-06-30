package com.example.vitameanshospitaldoctor.customview

class BarData: ChartData<IBarDataSet> {

    var barWidth: Float = 0.85f

    constructor(vararg dataSets: IBarDataSet): super(*dataSets)

    constructor(dataSets: List<IBarDataSet>): super(dataSets)
}