package com.example.vitameanshospitaldoctor.dialog

import androidx.lifecycle.*
import com.example.vitameanshospitaldoctor.data.entities.UserData
import com.example.vitameanshospitaldoctor.repository.UserRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NameSearchDialogVM @Inject constructor(
    private val userRepos: UserRepos
) : ViewModel() {

    var uName = MutableLiveData<String>()
    var result: LiveData<List<UserData>> = Transformations.switchMap(uName){
        userRepos.searchName(it).asLiveData()
    }

}
