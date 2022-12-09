package com.dsaa.mygit.api

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiManager {
    private val retrofit: Retrofit
    private val okHttpClient =
        OkHttpClient.Builder().addInterceptor(JSONHeaderInterceptor()).build()
    private val baseUrl = "https://api.github.com/"

    init {

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        private val manager = ApiManager()
        val client: Retrofit
            get() = manager.retrofit
        val service = ApiManager.client.create(ApiService::class.java)
    }

    class JSONHeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build()
            return chain.proceed(request)
        }
    }
}

