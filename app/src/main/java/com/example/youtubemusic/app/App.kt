package com.example.youtubemusic.app

import android.app.Application
import com.example.youtubemusic.data.SongsDatabase
import kotlinx.coroutines.InternalCoroutinesApi

class App : Application(){
    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        SongsDatabase.initializeDatabase(applicationContext)
    }
}