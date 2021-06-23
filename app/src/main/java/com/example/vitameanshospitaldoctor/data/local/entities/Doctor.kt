package com.example.vitameanshospitaldoctor.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Doctor (
    var hospital: String,
    var doctorname: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}