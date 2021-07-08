package com.example.youtubemusic.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Item(
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("id")
    val id: @RawValue Id?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("snippet")
    val snippet: @RawValue Snippet?,
    val isRotating: Boolean = false,
    val isResumed: Boolean = false,
    val isFavorite: Boolean = false
) : Parcelable