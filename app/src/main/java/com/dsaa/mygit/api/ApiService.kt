package com.dsaa.mygit.api

import com.dsaa.mygit.model.UserData
import com.dsaa.mygit.model.UserList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUser(@Query("since") since:String, @Query("per_page") page:String): Response<UserList>

    @GET("users/{username}")
    suspend fun getUserData(@Path(value = "username", encoded = true) username:String):Response<UserData>
}