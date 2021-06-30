package com.example.youtubemusic.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Client {

    val baseUrl = "https://www.googleapis.com/"
    val getDownloadLinkBaseUrl = "https://api.clickmp3.com/"

    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(1000, TimeUnit.MILLISECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create()).build()

    }

    val retrofit2 by lazy {
        Retrofit.Builder()
            .baseUrl(getDownloadLinkBaseUrl)
            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val api2: ApiService by lazy {
        retrofit2.create(ApiService::class.java)
    }
}