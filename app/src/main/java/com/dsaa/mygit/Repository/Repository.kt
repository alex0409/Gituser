package com.dsaa.mygit

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dsaa.mygit.api.ApiService
import com.dsaa.mygit.model.UserData
import com.dsaa.mygit.model.UserListItem
import com.dsaa.mygit.pagingsource.UserListPagingSource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor() {
    var pagesize = 30
    @Inject
    lateinit var Iservice: ApiService

    fun getPagingUserList(): Flow<PagingData<UserListItem>> {
        return Pager(config = PagingConfig(30), pagingSourceFactory = {
            UserListPagingSource(Iservice, pagesize.toString())
        }).flow
    }

    suspend fun getUserData(userName:String):UserData?{
        var result:UserData?
        val response = Iservice.getUserData(userName)
        result = response.body()

        return result
    }
}
