package com.example.vitameanshospitaldoctor.data

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.vitameanshospitaldoctor.data.dao.bloodPressureDataDao
import com.example.vitameanshospitaldoctor.data.dao.bloodSugarDataDao
import com.example.vitameanshospitaldoctor.data.dao.userDataDao
import com.example.vitameanshospitaldoctor.data.entities.bloodPressureData
import com.example.vitameanshospitaldoctor.data.entities.bloodSugarData
import com.example.vitameanshospitaldoctor.data.entities.userData

@Database(
    entities = [
        bloodSugarData::class,
        bloodPressureData::class,
        userData::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDataDao(): userDataDao
    abstract fun bloodPressureDataDao(): bloodPressureDataDao
    abstract fun bloodSugarDataDao(): bloodSugarDataDao


    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "vita-hospital-doctorview.db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<AppDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }
}