package com.dsaa.mygit.module

import android.util.Log
import com.dsaa.mygit.api.ApiManager
import com.dsaa.mygit.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    private val baseUrl = "https://api.github.com/"
    @Singleton
    @Provides
    fun provideGson():Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideHttpClient():OkHttpClient{
        val httpLog:HttpLoggingInterceptor = HttpLoggingInterceptor {
            Log.d("HttpLog:", it)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(ApiManager.JSONHeaderInterceptor())
            .addNetworkInterceptor(httpLog)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client:OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserListService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}