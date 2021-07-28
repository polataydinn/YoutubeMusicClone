package com.example.youtubemusic.app

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import com.example.youtubemusic.data.SongsDatabase
import com.example.youtubemusic.interfaces.PassDownloadManager
import kotlinx.coroutines.InternalCoroutinesApi

class App : Application(){
    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        SongsDatabase.initializeDatabase(applicationContext)
    }
}