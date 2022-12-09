package com.dsaa.mygit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsaa.mygit.api.ApiService
import com.dsaa.mygit.model.UserList
import com.dsaa.mygit.model.UserListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private  val api: ApiService
):ViewModel(){
    var UserList:MutableLiveData<UserList> = MutableLiveData()

    fun getUserList(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = api.getUser("1","30")
                if(result.isSuccessful){
                    Log.d("TAG", "getUserList: "+result.body())
                }

            }catch (ex:Exception){

            }
        }
    }
}