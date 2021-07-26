package com.example.youtubemusic.data

import androidx.room.TypeConverter
import com.example.youtubemusic.models.Source
import com.example.youtubemusic.models.Thumbnails

class SongsConverter {
    @TypeConverter
    fun fromString(source: Source): Thumbnails {
        return source.name
    }

    @TypeConverter
    fun toString(name: String): Thumbnails {
        return Source(name,name)
    }
}