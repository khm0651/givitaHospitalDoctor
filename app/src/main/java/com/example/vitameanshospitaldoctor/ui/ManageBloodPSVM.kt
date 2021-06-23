package com.example.vitameanshospitaldoctor.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.vitameanshospitaldoctor.DBdoctor
import com.example.vitameanshospitaldoctor.data.local.entities.Doctor

class ManageBloodPSVM(
    private val database: DBdoctor
): ViewModel() {

    val doctor: LiveData<List<Doctor>> = liveData {
        emitSource(database.doctorDao().getAllDoctor())
    }
}