package com.example.vitameanshospitaldoctor.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vitameanshospitaldoctor.data.entities.bloodPressureData
import com.example.vitameanshospitaldoctor.data.entities.userData

@Dao
interface bloodPressureDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bloodPressureDataList: List<bloodPressureData>)


}