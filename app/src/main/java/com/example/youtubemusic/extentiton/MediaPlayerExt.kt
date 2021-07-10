package com.example.youtubemusic.extentiton

import android.media.MediaPlayer
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


val MediaPlayer.info get() = MediaInfo()

fun MediaPlayer.isCurrent(otherMetaData: MetaData) =
    info.metaData != otherMetaData

class MediaInfo(){
    var _metaData = MetaData()
    var metaData: MetaData = _metaData
        set(value) {
            _metaData = value
        }
}
@Parcelize
data class MetaData(
    val id: String = UUID.randomUUID().toString(),
    val title: String = ""
): Parcelable