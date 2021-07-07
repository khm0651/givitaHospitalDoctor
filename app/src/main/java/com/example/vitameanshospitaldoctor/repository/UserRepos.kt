package com.example.vitameanshospitaldoctor.repository

import com.example.vitameanshospitaldoctor.data.dao.UserDataDao
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepos @Inject constructor(private val userDao: UserDataDao) {

    fun getUsers() = userDao.allUserData()

    fun searchName(userName: String) = userDao.searchName("%"+userName+"%")

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
        visitDate:Calendar?,
    ) = userDao.searchFilter(
        minAge,
        maxAge,
        sex,
        diseaseType,
        minBpShrinkage,
        maxBpShrinkage,
        minBpRelaxation,
        maxBpRelaxation,
        receiveOrNot,
        visitDate
    )

}