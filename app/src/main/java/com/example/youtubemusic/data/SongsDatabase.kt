package com.example.youtubemusic.data

import android.content.Context
import androidx.room.*
import com.example.youtubemusic.models.Item
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Item::class], version = 1)
@TypeConverters(SongsConverter::class)
abstract class SongsDatabase : RoomDatabase() {

    abstract fun dao(): SongsDao

    companion object {
        @Volatile
        private var INSTANCE: SongsDatabase? = null

        fun initializeDatabase(context: Context) {
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    SongsDatabase::class.java,
                    "Songs"
                ).build()
            }
        }

        fun getDatabase() = INSTANCE
    }
}