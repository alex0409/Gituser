package com.dsaa.mygit.api

import com.dsaa.mygit.model.UserList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/users")
    fun getreport(@Query("since") since:String, @Query("per_page") page:String): Call<UserList>
}