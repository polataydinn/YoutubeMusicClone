package com.example.youtubemusic.models


import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("videoId")
    val videoId: String?
)