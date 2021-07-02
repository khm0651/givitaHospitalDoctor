package com.example.vitameanshospitaldoctor.data.dao

import androidx.room.*
import com.example.vitameanshospitaldoctor.data.entities.UserData
import kotlinx.coroutines.flow.Flow
import java.util.*



@Dao
interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userDataList: List<UserData>)

    @Query("SELECT * FROM UserData")
    fun allUserData(): Flow<List<UserData>>

    @Query("SELECT * FROM UserData WHERE userName like :userName")
    fun searchName(userName: String): Flow<List<UserData>>

    @Query("SELECT * FROM UserData WHERE age>= :minAge AND age<= :maxAge AND sex = :sex AND diseaseType= :diseaseType AND lastVisitDate <= :minDate AND lastVisitDate >= :maxDate AND receiveOrNot = :receiveOrNot")
    fun searchFilter(
        minAge: Int,
        maxAge: Int,
        sex: String,
        diseaseType: String,
        minDate: Calendar,
        maxDate: Calendar,
        receiveOrNot: String
    ): List<UserData>
}