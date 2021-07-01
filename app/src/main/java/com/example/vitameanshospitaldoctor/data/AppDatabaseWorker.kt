package com.example.vitameanshospitaldoctor.data

import android.content.Context

import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.vitameanshospitaldoctor.BLOODPRESSURE_DATA_FILENAME
import com.example.vitameanshospitaldoctor.BLOODSUGAR_DATA_FILENAME
import com.example.vitameanshospitaldoctor.USER_DATA_FILENAME
import com.example.vitameanshospitaldoctor.data.entities.BloodPressureData
import com.example.vitameanshospitaldoctor.data.entities.BloodSugarData
import com.example.vitameanshospitaldoctor.data.entities.UserData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import java.lang.Exception
import java.util.*


class AppDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context,workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {

        try {


            val database = AppDatabase.getInstance(applicationContext)

            applicationContext.assets.open(USER_DATA_FILENAME).use { inputStream ->

                JsonReader(inputStream.reader()).use { jsonReader ->
                    val userType = object : TypeToken<List<UserData>>() {}.type
                    val userList: List<UserData> = Gson().fromJson(jsonReader, userType)
                    for (i in 0..userList.size - 1) {
                        userList[i].measureRequestDate = Calendar.getInstance()
                        userList[i].createdDate = Calendar.getInstance()
                        userList[i].lastVisitDate = Calendar.getInstance()
                        userList[i].bpRegistrationDate = Calendar.getInstance()
                        userList[i].bsRegistrationDate = Calendar.getInstance()
                    }
                    database.userDataDao().insertAll(userList)
                    Result.success()
                }
            }




           applicationContext.assets.open(BLOODPRESSURE_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val bpType = object : TypeToken<List<BloodPressureData>>() {}.type
                    val bpList: List<BloodPressureData> = Gson().fromJson(jsonReader, bpType)
                    for (i in 0..bpList.size - 1) {
                        bpList[i].measureDate = Calendar.getInstance()
                    }
                    database.bloodPressureDataDao().insertAll(bpList)
                    Result.success()
                }
            }


            applicationContext.assets.open(BLOODSUGAR_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val bsType = object : TypeToken<List<BloodSugarData>>() {}.type
                    val bsList: List<BloodSugarData> = Gson().fromJson(jsonReader, bsType)
                    for (i in 0..bsList.size - 1) {
                        bsList[i].measureDate = Calendar.getInstance()
                    }
                    database.bloodSugarDataDao().insertAll(bsList)
                    Result.success()
                }
            }



        } catch (e : Exception){
            Timber.e(e,"Error hospital database")
            Result.failure()
        }
    }
}