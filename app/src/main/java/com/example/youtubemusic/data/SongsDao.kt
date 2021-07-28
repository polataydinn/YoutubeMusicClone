package com.example.youtubemusic.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.youtubemusic.models.DownloadedFile
import com.example.youtubemusic.models.Item

@Dao
interface SongsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSongs(downloadedFile: DownloadedFile)

    @Query("SELECT * FROM Songs WHERE songUri IS NOT NULL")
    fun getAllColumns() : LiveData<List<DownloadedFile>>
}