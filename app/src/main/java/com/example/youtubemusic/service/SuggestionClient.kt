package com.example.youtubemusic.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object SuggestionClient {
    val baseUrl = "http://suggestqueries.google.com/"

    val retrofit:Retrofit by lazy {
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