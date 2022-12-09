package com.dsaa.mygit

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication:Application() {
    private val Tag = "Application"
    override fun onCreate() {
        super.onCreate()
        Log.d(Tag, "onCreate: ")
    }
}