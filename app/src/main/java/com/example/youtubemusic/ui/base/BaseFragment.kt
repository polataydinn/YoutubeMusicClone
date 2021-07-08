package com.example.youtubemusic.ui.base

import android.content.Context
import android.media.AudioManager
import android.util.SparseArray
import androidx.fragment.app.Fragment
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.youtubemusic.MainActivity
import com.example.youtubemusic.adapter.SearchAdapter
import com.example.youtubemusic.models.Item
import java.util.ArrayList


open class BaseFragment :Fragment() {

    private val BASEURL = "https://www.youtube.com/watch?v="


    fun getYoutubeDownloader(youtubeLink: String, context: Context, onResponse: (String) -> Unit) {
        object : YouTubeExtractor(context) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {

                if (ytFiles == null) {
                    return
                } else {
                    val itag = 140
                    val downloadUrl = ytFiles.get(itag).url
                    onResponse(downloadUrl)
                }
            }

        }.extract(youtubeLink, true, true)
    }

    fun playSong(list: ArrayList<Item>, position: Int, adapter: SearchAdapter, context: Context) {
        if (!(activity as MainActivity).player.isPlaying) {
            val currentVideoLink = BASEURL + list[position].id?.videoId
            getYoutubeDownloader(currentVideoLink, context) { audioUrl ->
                if (list[position].isResumed == true) {
                    (activity as MainActivity).player.start()
                }
                (activity as MainActivity).player.setAudioStreamType(AudioManager.STREAM_MUSIC)
                if ((activity as MainActivity).player.isPlaying == false && list[position].isResumed == false) {
                    try {
                        (activity as MainActivity).player.setDataSource(audioUrl)
                        (activity as MainActivity).player.prepare()
                        (activity as MainActivity).player.start()
                        (activity as MainActivity).songLenght =
                            milliSecondsToTimer((activity as MainActivity).player.duration.toLong())
                        if ((activity as MainActivity).player.isPlaying) {
                            for ((ind, item) in list.withIndex()) {
                                if (ind == position) {
                                    list[ind] =
                                        item.copy(isRotating = !item.isRotating)
                                    list[ind] =
                                        item.copy(isResumed = false)
                                } else {
                                    list[ind] = item.copy(isRotating = false)
                                }
                            }
                            adapter.submitList(list)
                        }
                    } catch (e: Exception) {
                    }
                } else {

                }
            }
        } else {
            for ((ind, item) in adapter.currentList.withIndex()) {
                if (ind == position) {
                    list[ind] = item.copy(isRotating = !item.isRotating)
                    list[ind] = item.copy(isResumed = true)
                } else {
                    list[ind] = item.copy(isRotating = false)
                }
            }
            (activity as MainActivity).player.pause()
            adapter.submitList(list)
        }
    }

    fun milliSecondsToTimer(ms: Long): String {
        var finalString = ""
        var secondString = ""

        val hours = (ms / (1000 * 60 * 60))
        val minutes = (ms % (1000 * 60 * 60)) / (1000 * 60)
        val seconds = ((ms % (1000 * 60 * 60)) % (1000 * 60) / 1000)

        if (hours > 0) {
            secondString = "$hours"
        }

        if (seconds < 10) {
            secondString = "0" + seconds
        } else {
            secondString = "" + seconds
        }

        finalString = finalString + minutes + ":" + secondString
        return finalString
    }

}