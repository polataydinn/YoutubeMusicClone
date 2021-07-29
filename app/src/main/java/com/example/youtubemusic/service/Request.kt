package com.example.youtubemusic.service

import android.util.Log
import com.example.youtubemusic.models.Item
import com.example.youtubemusic.models.Results
import com.example.youtubemusic.models.SearchSuggestion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Request {
    fun getSongs(songName: String, onResponse: (List<Item>) -> Unit) {
        Client.retrofit?.let { retrofit ->
            Client.api.getSongs(songName).enqueue(object : Callback<Results> {
                override fun onResponse(call: Call<Results>, response: Response<Results>) {
                    response.body()?.items?.let(onResponse)
                }

                override fun onFailure(call: Call<Results>, t: Throwable) {
                    Log.d("Hata", "Veri Çekiminde Hata Var")
                }
            })
        }
    }

    fun getSuggestions(searchWord: String, onResponse: (SearchSuggestion?) -> Unit){
        SuggestionClient.retrofit?.let {
            SuggestionClient.api.getSuggestions(searchWord).enqueue(object :
                Callback<SearchSuggestion> {
                override fun onResponse(
                    call: Call<SearchSuggestion>,
                    response: Response<SearchSuggestion>
                ) {
                    response.body().let(onResponse)
                }

                override fun onFailure(call: Call<SearchSuggestion>, t: Throwable) {
                    Log.d("TAG", "onFailure: Fail")
                }

            })
        }
    }
}