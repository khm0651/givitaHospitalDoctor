package com.example.vitameanshospitaldoctor.ui.bp_bs

import androidx.lifecycle.*
import com.example.vitameanshospitaldoctor.data.entities.UserData
import com.example.vitameanshospitaldoctor.repository.UserRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageBloodPSTableVM @Inject constructor(
    private val userRepos: UserRepos
) : ViewModel() {
    val userList : LiveData<List<UserData>> = userRepos.getUsers().asLiveData()

}
