package com.example.vitameanshospitaldoctor.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class bloodPressureData(
    val adminId: String,
    val measureDate: Calendar = Calendar.getInstance(),
    val measurePoint: String,
    val state: String,
    val shrinkage: Int,
    val relaxation: Int
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
