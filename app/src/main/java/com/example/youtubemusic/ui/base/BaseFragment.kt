package com.example.youtubemusic.ui.base

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.os.Environment
import android.util.SparseArray
import android.webkit.DownloadListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.youtubemusic.MainActivity
import com.example.youtubemusic.adapter.SearchAdapter
import com.example.youtubemusic.models.Item
import java.util.*


open class BaseFragment : Fragment() {

    private val BASEURL = "https://www.youtube.com/watch?v="
    var tempData: String? = null

    @SuppressLint("StaticFieldLeak")
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

    fun downloadSong(item: Item, context: Context) {
        val currentVideoLink = BASEURL + item._id?.videoId
        getYoutubeDownloader(currentVideoLink, context) { songUrl ->
            val request = DownloadManager.Request(Uri.parse(songUrl))
            request.setTitle(item.snippet?.title + ".mp3")
            request.setDescription("Your Song is Downloading..")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                item.snippet?.title
            )
            (activity as MainActivity).downloadManager.enqueue(request)

            Toast.makeText(context, "Your Download is Started", Toast.LENGTH_SHORT).show()
        }


    }


    fun playSong(list: ArrayList<Item>, position: Int, adapter: SearchAdapter, context: Context) {
        if ((activity as MainActivity).player.isPlaying &&
            tempData != list[position].uuid
        ) {
            (activity as MainActivity).player.reset()
            tempData = list[position].uuid
            playSong(list, position, adapter, context)
        } else if (!(activity as MainActivity).player.isPlaying) {
            tempData = list[position].uuid
            val currentVideoLink = BASEURL + list[position]._id?.videoId
            getYoutubeDownloader(currentVideoLink, context) { audioUrl ->
                if (list[position].isResumed && tempData == list[position].uuid) {
                    (activity as MainActivity).player.start()
                    updateList(list, position, adapter)
                    updateResumedList(list, position, adapter)
                }
                if (!(activity as MainActivity).player.isPlaying && !list[position].isResumed
                ) {
                    try {
                        (activity as MainActivity).player.setAudioStreamType(AudioManager.STREAM_MUSIC)
                        (activity as MainActivity).player.setDataSource(audioUrl)
                        (activity as MainActivity).player.prepare()
                        (activity as MainActivity).player.start()
                        (activity as MainActivity).songLenght =
                            milliSecondsToTimer((activity as MainActivity).player.duration.toLong())
                        if ((activity as MainActivity).player.isPlaying) {
                            updateList(list, position, adapter)
                        }
                    } catch (e: Exception) {
                    }
                } else {
                }
            }
        } else {
            (activity as MainActivity).player.pause()
            updateList(list, position, adapter)
            updateResumedList(list, position, adapter)
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

    fun updateList(list: ArrayList<Item>, position: Int, adapter: SearchAdapter) {
        for ((ind, item) in list.withIndex()) {
            if (ind == position) {
                list[ind] =
                    item.copy(isPlaying = !item.isPlaying)
            } else {
                list[ind] = item.copy(isPlaying = false)
            }
        }
        adapter.submitList(list)
    }

    fun updateResumedList(list: ArrayList<Item>, position: Int, adapter: SearchAdapter) {
        for ((ind, item) in list.withIndex()) {
            if (ind == position) {
                list[ind] = item.copy(isResumed = !item.isResumed)
            } else {
                list[ind] == item.copy(isResumed = false)
            }
        }
        adapter.submitList(list)
    }
}