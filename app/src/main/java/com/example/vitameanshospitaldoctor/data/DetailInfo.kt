package com.example.vitameanshospitaldoctor.data

data class DetailInfo(
    var day: String,
    var date: String,
    var detailBloodPressure: DetailBloodPressure?,
    var detailBloodSugar: DetailBloodSugar?,
    var viewType: Int
){
    companion object{
        const val SHOW_BOTH = 0
        const val SHOW_ONE = 1
    }
}
