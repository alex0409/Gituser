package com.dsaa.mygit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dsaa.mygit.Repository
import com.dsaa.mygit.api.ApiService
import com.dsaa.mygit.model.UserList
import com.dsaa.mygit.model.UserListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    val api: ApiService,
    val repository: Repository
):ViewModel(){
    var UserList:MutableLiveData<UserList> = MutableLiveData()

    fun getUserList(): kotlinx.coroutines.flow.Flow<PagingData<UserListItem>> {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val result = api.getUser("1","30")
//                if(result.isSuccessful){
//                    Log.d("TAG", "getUserList: "+result.body())
//                }
//
//            }catch (ex:Exception){
//
//            }
//        }
        return repository.getPagingUserList().cachedIn(viewModelScope)
    }
}