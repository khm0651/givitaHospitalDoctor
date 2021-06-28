package com.example.vitameanshospitaldoctor.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.vitameanshospitaldoctor.data.Converters
import java.util.*


@Entity
data class userData(
    @ColumnInfo(name = "adminId") val adminId: String,
    @ColumnInfo(name = "createdDate") var createdDate: Calendar,
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "sex") var sex: String,
    @ColumnInfo(name = "age") var age: Int,
    @ColumnInfo(name = "shrinkage") var shrinkage: Int?,
    @ColumnInfo(name = "relaxation") var relaxation: Int?,
    @ColumnInfo(name = "bpRegistrationDate") var bpRegistrationDate: Calendar?,
    @ColumnInfo(name = "bloodSugar") var bloodSugar: Int?,
    @ColumnInfo(name = "bsRegistrationDate") var bsRegistrationDate: Calendar?,
    @ColumnInfo(name = "diseaseType") var diseaseType: String?,
    @ColumnInfo(name = "bloodPressureState") var bloodPressureState: String?,
    @ColumnInfo(name = "lastVisitDate") var lastVisitDate: Calendar?,
    @ColumnInfo(name = "measureRequestDate") var measureRequestDate: Calendar?,
    @ColumnInfo(name = "receiveOrNot") var receiveOrNot: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
