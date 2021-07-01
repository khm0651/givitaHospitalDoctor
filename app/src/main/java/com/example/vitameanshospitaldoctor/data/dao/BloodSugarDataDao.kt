package com.example.vitameanshospitaldoctor.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.vitameanshospitaldoctor.data.entities.BloodSugarData


@Dao
interface BloodSugarDataDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bloodSugarDataList: List<BloodSugarData>)


}