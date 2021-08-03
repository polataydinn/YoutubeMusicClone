package com.example.youtubemusic.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubemusic.models.Item
import com.example.youtubemusic.service.Request
import java.util.*

class SearchViewModel : ViewModel() {
    val songList = MutableLiveData<List<Item>>()

    fun getListOfSearch(songName: String) {
        Request.getSongs(songName) {
            it.forEach {
                it.uuid = UUID.randomUUID().toString()
            }
            songList.postValue(it)
        }
    }
}