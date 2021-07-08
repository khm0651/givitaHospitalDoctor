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

    @Query("SELECT * FROM UserData WHERE age>= :minAge AND age<= :maxAge AND sex  NOT IN (:sex) AND diseaseType NOT IN (:diseaseType) AND shrinkage >= :minBpShrinkage AND shrinkage <= :maxBpShrinkage AND relaxation >= :minBpRelaxation  AND relaxation <= :maxBpRelaxation AND receiveOrNot NOT IN (:receiveOrNot) AND lastVisitDate < :visitDate")
    fun searchFilter(
        minAge: Int?,
        maxAge: Int?,
        sex: String?,
        diseaseType: String?,
        minBpShrinkage: Int?,
        maxBpShrinkage: Int?,
        minBpRelaxation: Int?,
        maxBpRelaxation: Int?,
        receiveOrNot: String?,
        visitDate:Calendar?
    ): Flow<List<UserData>>
}