package com.example.vitameanshospitaldoctor.data

import android.content.Context

import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.vitameanshospitaldoctor.BLOODPRESSURE_DATA_FILENAME
import com.example.vitameanshospitaldoctor.BLOODSUGAR_DATA_FILENAME
import com.example.vitameanshospitaldoctor.USER_DATA_FILENAME
import com.example.vitameanshospitaldoctor.data.entities.bloodPressureData
import com.example.vitameanshospitaldoctor.data.entities.bloodSugarData
import com.example.vitameanshospitaldoctor.data.entities.userData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import java.lang.Exception

class AppDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context,workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {
        try {

            val database = AppDatabase.getInstance(applicationContext)


            applicationContext.assets.open(USER_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val userType = object : TypeToken<List<userData>>() {}.type
                    val userList: List<userData> = Gson().fromJson(jsonReader, userType)
                    database.userDataDao().insertAll(userList)
                    Result.success()
                }
            }


            applicationContext.assets.open(BLOODPRESSURE_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val bpType = object : TypeToken<List<bloodPressureData>>() {}.type
                    val bpList: List<bloodPressureData> = Gson().fromJson(jsonReader, bpType)
                    database.bloodPressureDataDao().insertAll(bpList)
                    Result.success()
                }
            }


            applicationContext.assets.open(BLOODSUGAR_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val bsType = object : TypeToken<List<bloodSugarData>>() {}.type
                    val bsList: List<bloodSugarData> = Gson().fromJson(jsonReader, bsType)
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