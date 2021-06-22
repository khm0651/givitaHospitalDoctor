package com.example.vitameanshospitaldoctor.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class bloodPressureData(
    @PrimaryKey(autoGenerate = true)val id: Int,
    @ColumnInfo(name = "adminId")val adminId: String,
    @ColumnInfo(name = "measureDate")val measureDate: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "measurePoint")val measurePoint: String,
    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "shrinkage") val shrinkage: Int,
    @ColumnInfo(name = "relaxation") val relaxation: Int,
    @ColumnInfo(name = "userName") var userName: String
)
