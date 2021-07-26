package com.example.youtubemusic.data

import com.example.youtubemusic.models.Item

object SongsRepository {
    private val songsDao by lazy {
        SongsDatabase.getDatabase()?.dao()
    }

    suspend fun addSongsToDB(item : Item){
        songsDao?.addSongs(item)
    }
}