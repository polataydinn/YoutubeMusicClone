package com.example.youtubemusic.interfaces

import com.example.youtubemusic.models.Item

interface PassDataInterface{
    fun onDataRecieved(item : Item)
}