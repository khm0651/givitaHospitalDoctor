package com.example.vitameanshospitaldoctor.dialog

import androidx.lifecycle.*
import com.example.vitameanshospitaldoctor.data.entities.UserData
import com.example.vitameanshospitaldoctor.repository.UserRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject


@HiltViewModel
class FilterSearchDialogVM @Inject constructor(
    private val userRepos: UserRepos
) : ViewModel() {

    var minAge = MutableLiveData<Int>()
    var maxAge = MutableLiveData<Int>()
    var gender = MutableLiveData<String>()
    var type = MutableLiveData<String>()
    var minBpShrinkage = MutableLiveData<Int>()
    var maxBpShrinkage = MutableLiveData<Int>()
    var minBpRelaxation = MutableLiveData<Int>()
    var maxBpRelaxation = MutableLiveData<Int>()
    var visitDate = MutableLiveData<String>()
    var receive = MutableLiveData<String>()


    var result: LiveData<List<UserData>> = Transformations.switchMap(receive) {
        search()
    }

    fun search(): LiveData<List<UserData>>{
        var minAge1 = minAge.value?.toInt()
        var maxAge1 = maxAge.value?.toInt()
        var gender1 = "Not Null"
        if ( gender.value=="남성"){
            gender1 = "여성"
        }
        else if (gender.value=="여성"){
            gender1 = "남성"
        }
        var type1 = "Not Null"
        if ( type.value=="1형" ){
            type1 = "2형"
        }
        else if (type.value=="2형"){
            type1 = "1형"
        }

        var minBpShrinkage1 = minBpShrinkage.value?.toInt()
        var maxBpShrinkage1 = maxBpShrinkage.value?.toInt()

        var minBpRelaxation = minBpRelaxation.value?.toInt()
        var maxBpRelaxation = maxBpRelaxation.value?.toInt()

        val cal = Calendar.getInstance()
        if (visitDate.value=="1주일"){
            cal.add(Calendar.DAY_OF_WEEK,-1)
        }
        else if (visitDate.value=="1개월"){
            cal.add(Calendar.MONTH,-1)
        }
        else if (visitDate.value=="3개월"){
            cal.add(Calendar.MONTH,-3)
        }
        else if (visitDate.value=="6개월"){
            cal.add(Calendar.MONTH,-6)
        }

        var receive1 = "Not Null"

        if ( receive.value=="Y" ){
            receive1 = "N"
        }
        else if (receive.value=="N"){
            receive1 = "Y"
        }

        return userRepos.searchFilter(minAge1,maxAge1,gender1,type1,minBpShrinkage1,maxBpShrinkage1,minBpRelaxation,maxBpRelaxation,receive1,cal).asLiveData()

    }

}