package com.example.youtubemusic.service

import android.util.Log
import com.example.youtubemusic.models.DownloadLink
import com.example.youtubemusic.models.Item
import com.example.youtubemusic.models.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Request {
    fun getSongs(songName : String,onResponse: (List<Item>) -> Unit) {
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

    fun getDownloadLink(songLink : String, onResponse: (String?) -> Unit){
        Client.retrofit2.let { retrofit ->
            Client.api2.getDownloadLink(songLink).enqueue(object  : Callback<DownloadLink>{
                override fun onFailure(call: Call<DownloadLink>, t: Throwable) {

                }
                override fun onResponse(
                    call: Call<DownloadLink>,
                    response: Response<DownloadLink>
                ) {
                    response.body()?.url.let(onResponse)
                }

            })
        }
    }
}