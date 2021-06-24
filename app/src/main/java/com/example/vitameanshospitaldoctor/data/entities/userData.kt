package com.example.vitameanshospitaldoctor.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class userData(
    val adminId: String,
    var createdDate: Calendar = Calendar.getInstance(),
    var userName: String,
    var sex: String,
    var age: Int,
    var shrinkage: Int?,
    var relaxation: Int?,
    var bpRegistrationDate: Calendar?,
    var bloodSugar: Int?,
    var bsRegistrationDate: Calendar?,
    var diseaseType: String?,
    var bloodPressureState: String?,
    var lastVisitDate: Calendar?,
    var measureRequestDate: Calendar?,
    var receiveOrNot: String
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
