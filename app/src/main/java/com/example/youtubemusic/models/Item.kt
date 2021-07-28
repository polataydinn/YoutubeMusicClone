package com.example.youtubemusic.models



import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



data class Item(
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("id")
    val _id: Id?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("snippet")
    val snippet: Snippet?,
    val isPlaying: Boolean = false,
    val isResumed: Boolean = false,
    @PrimaryKey
    var uuid: String
)