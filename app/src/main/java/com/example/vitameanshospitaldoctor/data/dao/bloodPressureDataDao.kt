package com.example.vitameanshospitaldoctor.data.dao

import androidx.room.*
import com.example.vitameanshospitaldoctor.data.Converters
import com.example.vitameanshospitaldoctor.data.entities.bloodPressureData
import com.example.vitameanshospitaldoctor.data.entities.userData

@Dao
interface bloodPressureDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bloodPressureDataList: List<bloodPressureData>)


}