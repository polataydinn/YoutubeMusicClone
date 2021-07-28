package com.example.youtubemusic.data

import com.example.youtubemusic.models.DownloadedFile
import com.example.youtubemusic.models.Item

object SongsRepository {
    private val songsDao by lazy {
        SongsDatabase.getDatabase()?.dao()
    }

    suspend fun addSongsToDB(downloadedFile: DownloadedFile ){
        songsDao?.addSongs(downloadedFile)
    }

    fun readAllSongs() = songsDao?.getAllColumns()
}