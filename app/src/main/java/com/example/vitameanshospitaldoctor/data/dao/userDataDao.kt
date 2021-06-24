package com.example.vitameanshospitaldoctor.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vitameanshospitaldoctor.data.entities.userData
import java.util.*
import java.util.concurrent.Flow


@Dao
interface userDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userDataList: List<userData>)

    @Query("SELECT * FROM userData ORDER BY id DESC")
    fun getAllUserData(): List<userData>

    @Query("SELECT * FROM userData WHERE userName like :userName")
    fun searchName(userName: String): List<userData>

    @Query("SELECT * FROM userData WHERE age>= :minAge AND age<= :maxAge AND sex = :sex AND diseaseType= :diseaseType AND lastVisitDate <= :minDate AND lastVisitDate >= :maxDate AND receiveOrNot = :receiveOrNot")
    fun searchFilter(
        minAge: Int,
        maxAge: Int,
        sex: String,
        diseaseType: String,
        minDate: Calendar,
        maxDate: Calendar,
        receiveOrNot: String
    ): List<userData>
}