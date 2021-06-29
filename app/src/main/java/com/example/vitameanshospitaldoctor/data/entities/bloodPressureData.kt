package com.example.vitameanshospitaldoctor.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.vitameanshospitaldoctor.data.Converters
import java.util.*

@Entity
@TypeConverters(Converters::class)
data class bloodPressureData(
    @ColumnInfo(name = "adminId") val adminId: String,
    @ColumnInfo(name ="measureDate") var measureDate: Calendar,
    @ColumnInfo(name ="measurePoint") val measurePoint: String,
    @ColumnInfo(name ="state") val state: String,
    @ColumnInfo(name = "shrinkage") val shrinkage: Int,
    @ColumnInfo(name = "relaxation") val relaxation: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0
}
