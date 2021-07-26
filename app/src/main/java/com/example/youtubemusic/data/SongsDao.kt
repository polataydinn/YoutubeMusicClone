package com.example.youtubemusic.data

import androidx.room.*
import com.example.youtubemusic.models.Item

@Dao
interface SongsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSongs(item: Item)
}