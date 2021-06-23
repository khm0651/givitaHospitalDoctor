package com.example.vitameanshospitaldoctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vitameanshospitaldoctor.data.local.dao.DoctorDao
import com.example.vitameanshospitaldoctor.data.local.entities.Doctor

class MainActivity : AppCompatActivity() {

    private lateinit var doctorDao: DoctorDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        accessDatabase()
        Thread(insertDoctor).start()
    }

    private fun accessDatabase() {
        val DoctorDatabase = DBdoctor.getInstance(this)!!
        doctorDao = DoctorDatabase.doctorDao()
    }

    private  val insertDoctor = Runnable {
        val entity = Doctor (
            hospital = "지아이비타",
            doctorname = "김주영"
                )
        doctorDao.insert(entity)
    }
}