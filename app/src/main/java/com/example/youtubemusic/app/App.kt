package com.example.youtubemusic.app

import android.app.Application
import com.example.youtubemusic.data.SongsDatabase

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        SongsDatabase.initializeDatabase(applicationContext)
    }
}