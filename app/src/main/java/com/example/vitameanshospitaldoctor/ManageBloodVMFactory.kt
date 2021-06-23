package com.example.vitameanshospitaldoctor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vitameanshospitaldoctor.ui.ManageBloodPSVM
import java.lang.IllegalArgumentException

class ManageBloodVMFactory(private val database: DBdoctor): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom((ManageBloodPSVM::class.java))){
            ManageBloodPSVM(database) as T
        }else{
            throw IllegalArgumentException()
        }
    }
}