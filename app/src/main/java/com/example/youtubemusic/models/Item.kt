package com.example.youtubemusic.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Songs")
data class Item(
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("snippet")
    val snippet: Snippet?,
    val isPlaying: Boolean = false,
    val isResumed: Boolean = false,
    @PrimaryKey
    var uuid: String
)