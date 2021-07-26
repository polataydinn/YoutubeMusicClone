package com.example.youtubemusic.models

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val _id: Any,
    @SerializedName("name")
    val name: String
)