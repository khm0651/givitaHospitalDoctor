package com.example.vitameanshospitaldoctor.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.vitameanshospitaldoctor.data.dao.BloodPressureDataDao
import com.example.vitameanshospitaldoctor.data.dao.BloodSugarDataDao
import com.example.vitameanshospitaldoctor.data.dao.UserDataDao
import com.example.vitameanshospitaldoctor.data.entities.BloodPressureData
import com.example.vitameanshospitaldoctor.data.entities.BloodSugarData
import com.example.vitameanshospitaldoctor.data.entities.UserData

@Database(
    entities = [
        BloodSugarData::class,
        BloodPressureData::class,
        UserData::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDataDao(): UserDataDao
    abstract fun bloodPressureDataDao(): BloodPressureDataDao
    abstract fun bloodSugarDataDao(): BloodSugarDataDao


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