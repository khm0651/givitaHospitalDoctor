package com.example.vitameanshospitaldoctor.repository

import com.example.vitameanshospitaldoctor.data.dao.userDataDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepos @Inject constructor(private val userDao: userDataDao) {

    fun getUsers() = userDao.allUserData()

    fun searchName(userName: String) = userDao.searchName(userName)

    fun searchFilter(
        minAge: Int,
        maxAge: Int,
        sex: String,
        diseaseType: String,
        minDate: Calendar,
        maxDate: Calendar,
        receiveOrNot: String
    ) = userDao.searchFilter(
        minAge, maxAge,
        sex,
        diseaseType,
        minDate,
        maxDate,
        receiveOrNot
    )

}