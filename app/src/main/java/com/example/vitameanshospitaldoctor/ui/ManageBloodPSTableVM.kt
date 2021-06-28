package com.example.vitameanshospitaldoctor.ui

import androidx.lifecycle.*
import com.example.vitameanshospitaldoctor.data.entities.userData
import com.example.vitameanshospitaldoctor.repository.UserRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageBloodPSTableVM @Inject constructor(
    private val userRepos: UserRepos
) : ViewModel() {
    val userList : LiveData<List<userData>> = userRepos.getUsers().asLiveData()


}
