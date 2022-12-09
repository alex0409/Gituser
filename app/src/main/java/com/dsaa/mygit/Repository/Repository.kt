package com.dsaa.mygit

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dsaa.mygit.api.ApiService
import com.dsaa.mygit.model.UserListItem
import com.dsaa.mygit.pagingsource.UserListPagingSource
import kotlinx.coroutines.flow.Flow
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
}
