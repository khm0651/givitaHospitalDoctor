package com.example.vitameanshospitaldoctor.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.TypeConverters
import com.example.vitameanshospitaldoctor.data.Converters
import com.example.vitameanshospitaldoctor.data.entities.bloodPressureData
import com.example.vitameanshospitaldoctor.data.entities.bloodSugarData


@Dao
interface bloodSugarDataDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bloodSugarDataList: List<bloodSugarData>)


}