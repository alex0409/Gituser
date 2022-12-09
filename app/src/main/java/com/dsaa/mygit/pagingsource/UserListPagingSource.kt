package com.dsaa.mygit.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dsaa.mygit.api.ApiService
import com.dsaa.mygit.model.UserListItem

class UserListPagingSource(val service:ApiService,val pageSize:String): PagingSource<Int, UserListItem>() {
    override fun getRefreshKey(state: PagingState<Int, UserListItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult.Page<Int, UserListItem> {
        // Start refresh at page 1 if undefined.
        try {
            val response = service.getUser(params.key.toString(),pageSize)
            if(response.isSuccessful){
                return response.body()?.let {
                    LoadResult.Page(
                        it.toList(),prevKey = null,nextKey = response.body()
                            ?.get(29)?.id)
                }!!
            }else{
                return LoadResult.Page(ArrayList(),null,null)
            }

        }catch (e:Exception){
            return LoadResult.Page(ArrayList(),null,null)
        }
    }


}