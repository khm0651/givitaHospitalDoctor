package com.example.vitameanshospitaldoctor.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class bloodSugarData(
    @PrimaryKey(autoGenerate = true)val id: Int,
    @ColumnInfo(name = "adminId")val adminId: String,
    @ColumnInfo(name = "measureDate")val measureDate: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "measurePoint")val measurePoint: String,
    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "bloodSugar") val bloodSugar: Int,
    @ColumnInfo(name = "userName") var userName: String
)
