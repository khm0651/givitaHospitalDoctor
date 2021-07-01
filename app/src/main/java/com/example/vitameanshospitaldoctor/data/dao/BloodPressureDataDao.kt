package com.example.vitameanshospitaldoctor.data.dao

import androidx.room.*
import com.example.vitameanshospitaldoctor.data.entities.BloodPressureData

@Dao
interface BloodPressureDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bloodPressureDataList: List<BloodPressureData>)


}