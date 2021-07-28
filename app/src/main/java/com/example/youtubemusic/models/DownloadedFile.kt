package com.example.youtubemusic.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Songs")
data class DownloadedFile(
    val songUri : String,
    val songName : String,
    val songArtist : String,
    @PrimaryKey
    val uuid : String
)
