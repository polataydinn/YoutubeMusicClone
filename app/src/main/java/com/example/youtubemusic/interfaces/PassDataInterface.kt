package com.example.youtubemusic.interfaces

import android.app.DownloadManager
import android.media.MediaPlayer
import com.example.youtubemusic.models.Item

interface PassDataInterface{
    fun onDataRecieved(item : Item)

    fun onPlayerRecieved(downloadManager: DownloadManager)
}