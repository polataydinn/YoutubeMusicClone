package com.example.youtubemusic.data

import androidx.room.TypeConverter
import com.example.youtubemusic.models.*
import com.google.gson.Gson

class SongsConverter {
    @TypeConverter
    fun fromId(id: Id): String = Gson().toJson(id)

    @TypeConverter
    fun toId(id: String): Id = Gson().fromJson(id, Id::class.java)

    @TypeConverter
    fun fromSnippet(snippet: Snippet): String = Gson().toJson(snippet)

    @TypeConverter
    fun toSnippet(snippet: String) : Snippet = Gson().fromJson(snippet, Snippet::class.java)
}