package com.example.vitameanshospitaldoctor.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vitameanshospitaldoctor.ui.detail.DetailType.*

class DetailInfoVM: ViewModel() {

    private val _bpCheck = MutableLiveData<Boolean>(true)
    val bpCheck: LiveData<Boolean>
        get() = _bpCheck

    private val _bsCheck = MutableLiveData<Boolean>(true)
    val bsCheck: LiveData<Boolean>
        get() = _bsCheck

    private val _isBoth = MutableLiveData<DetailType>(Both)
    val isBoth: LiveData<DetailType>
        get() = _isBoth

    fun setBpCheck(value: Boolean){
        _bpCheck.value = value
    }

    fun setBsCheck(value: Boolean){
        _bsCheck.value = value
    }

    fun isBoth(){
        if(_bpCheck.value == true && _bsCheck.value == true) _isBoth.value = Both
        else if(_bpCheck.value == true) _isBoth.value = BP
        else if(_bsCheck.value == true) _isBoth.value = BS
        else _isBoth.value = Nothing
    }
}

sealed class DetailType{
    object Both: DetailType()
    object BP: DetailType()
    object BS: DetailType()
    object Nothing: DetailType()
}