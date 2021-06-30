package com.example.youtubemusic.service

import com.example.youtubemusic.models.DownloadLink
import com.example.youtubemusic.models.Results
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("youtube/v3/search?part=snippet&maxResults=20&type=video&key=AIzaSyB9Pp1nvutd-_ncryCbw_WqVrBTiBUUoA8\n")
    fun getSongs(@Query("q") songName : String): Call<Results>

    @Headers("content-type: application/json")
    @POST("file/link")
    fun getDownloadLink(@Body videoUrl: String): Call<DownloadLink>

}