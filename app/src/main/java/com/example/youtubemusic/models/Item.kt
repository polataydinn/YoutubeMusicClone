package com.example.youtubemusic.models


import android.os.Parcelable
import com.example.youtubemusic.extentiton.MetaData
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Item (
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("id")
    val id: @RawValue Id?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("snippet")
    val snippet: Snippet?,
    val isPlaying: Boolean = false,
    val isResumed: Boolean = false,
    var isFavorite: Int? = 56,
) : Parcelable{
    val metaData: MetaData get() = MetaData(title = snippet?.title.toString())
}