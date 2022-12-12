package com.dsaa.mygit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsaa.mygit.Repository
import com.dsaa.mygit.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SecondViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {
    var userData:MutableLiveData<UserData?> = MutableLiveData()
    fun getUserData(username:String){
        viewModelScope.launch {
           val result =  repository.getUserData(username)
            userData.value = result
        }
    }
}