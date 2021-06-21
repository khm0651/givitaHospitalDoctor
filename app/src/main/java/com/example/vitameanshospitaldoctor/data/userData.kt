package com.example.vitameanshospitaldoctor.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class userData(
    @PrimaryKey(autoGenerate = true)val id: Int,
    @ColumnInfo(name = "adminId")val adminId: String,
    @ColumnInfo(name = "createdDate")var createdDate: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "sex")var sex: String,
    @ColumnInfo(name = "age")var age: Int,
    @ColumnInfo(name = "diseaseType")var diseaseType: String,
    @ColumnInfo(name = "lastVisitDate")var lastVisitDate: String,
    @ColumnInfo(name = "measureRequestDate")var measureRequestDate: String,
    @ColumnInfo(name = "receiveOrNot")var receiveOrNot: String
    )
