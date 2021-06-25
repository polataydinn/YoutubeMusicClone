package com.example.youtubemusic.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Client {

    val baseUrl = "https://www.googleapis.com/"

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

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}