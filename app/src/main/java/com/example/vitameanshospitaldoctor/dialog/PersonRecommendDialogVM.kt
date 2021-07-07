package com.example.vitameanshospitaldoctor.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PersonRecommendDialogVM: ViewModel() {

    var availabilityState = MutableLiveData<Boolean>(false)
}