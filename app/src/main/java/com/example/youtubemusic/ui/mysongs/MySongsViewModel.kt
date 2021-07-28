package com.example.youtubemusic.ui.mysongs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtubemusic.data.SongsRepository
import com.example.youtubemusic.models.DownloadedFile
import kotlinx.coroutines.launch

class MySongsViewModel : ViewModel() {
    private var _songs = getDownloadedSongs()
    val songs = _songs

    fun updateDownloadedSongs(downloadedFile: DownloadedFile){
        viewModelScope.launch {
            updateDownloadedSongs(downloadedFile)
        }
    }

    fun getDownloadedSongs() = SongsRepository.readAllSongs()

}