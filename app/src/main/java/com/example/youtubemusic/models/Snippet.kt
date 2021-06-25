package com.example.youtubemusic.models


import com.google.gson.annotations.SerializedName

data class Snippet(
    @SerializedName("channelId")
    val channelId: String?,
    @SerializedName("channelTitle")
    val channelTitle: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("liveBroadcastContent")
    val liveBroadcastContent: String?,
    @SerializedName("publishTime")
    val publishTime: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("thumbnails")
    val thumbnails: Thumbnails?,
    @SerializedName("title")
    val title: String?
)