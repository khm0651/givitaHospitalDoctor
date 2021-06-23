package com.example.vitameanshospitaldoctor.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vitameanshospitaldoctor.data.local.entities.Doctor
import kotlinx.coroutines.flow.Flow


@Dao
interface DoctorDao{
    @Insert
    fun insert(doctor: Doctor)

    @Update
    fun update(doctor: Doctor)

    @Delete
    fun delete(doctor: Doctor)

    @Query("SELECT * FROM Doctor")
    fun getAllDoctor(): LiveData<List<Doctor>>


}
