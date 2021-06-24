package com.example.vitameanshospitaldoctor.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class bloodSugarData(
    val adminId: String,
    val measureDate: Calendar = Calendar.getInstance(),
    val measurePoint: String,
    val state: String,
    val bloodSugar: Int
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
