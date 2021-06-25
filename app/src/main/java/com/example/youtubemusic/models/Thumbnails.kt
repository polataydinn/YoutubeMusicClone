package com.example.youtubemusic.models


import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @SerializedName("default")
    val default: Default?,
    @SerializedName("high")
    val high: High?,
    @SerializedName("medium")
    val medium: Medium?
)