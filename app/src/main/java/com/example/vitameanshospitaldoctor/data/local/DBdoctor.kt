package com.example.vitameanshospitaldoctor

import android.content.Context
import androidx.room.*
import com.example.vitameanshospitaldoctor.data.local.dao.DoctorDao
import com.example.vitameanshospitaldoctor.data.local.entities.Doctor


@Database(entities = [Doctor::class], version = 1)
abstract class DBdoctor: RoomDatabase() {
    abstract fun doctorDao(): DoctorDao

    companion object {
        private var INSTANCE: DBdoctor? = null

        fun getInstance(context: Context): DBdoctor? {
            if (INSTANCE == null) {
                synchronized(DBdoctor::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DBdoctor::class.java,
                        "DBdoctor"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}



