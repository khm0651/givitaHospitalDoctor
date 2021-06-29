package com.example.vitameanshospitaldoctor.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.vitameanshospitaldoctor.data.Converters
import java.util.*

@Entity
@TypeConverters(Converters::class)
data class bloodSugarData(
    val adminId: String,
    var measureDate: Calendar,
    val measurePoint: String,
    val state: String,
    val bloodSugar: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
