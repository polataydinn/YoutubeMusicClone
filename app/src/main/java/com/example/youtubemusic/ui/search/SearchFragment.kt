package com.example.youtubemusic.ui.search

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.youtubemusic.adapter.SearchAdapter
import com.example.youtubemusic.databinding.FragmentSearchBinding
import com.example.youtubemusic.service.Request
import java.util.*
import android.view.KeyEvent
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.youtubemusic.R
import com.example.youtubemusic.interfaces.PassDataInterface
import com.example.youtubemusic.models.Item


class SearchFragment : Fragment() {

    private val BASEURL = "https://www.youtube.com/watch?v="
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SearchAdapter
    private val player: MediaPlayer = MediaPlayer()
    private lateinit var passDataInterface: PassDataInterface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SearchAdapter { position, case ->
            val list = ArrayList(adapter.currentList)
            val item = list[position]
            passDataInterface.onDataRecieved(item)
            when (case) {
                0 -> {

                }
                1 -> {
                    playSong(list, position)
                    val image = activity?.findViewById<ImageView>(R.id.bottomSongImage)
                    val bottomPlayer: RelativeLayout =
                        activity?.findViewById(R.id.bottomPlayerController)!!
                    bottomPlayer.visibility = VISIBLE

                    if (image != null) {
                        Glide.with(image).load(list[position].snippet?.thumbnails?.high?.url)
                            .diskCacheStrategy(DiskCacheStrategy.DATA).into(image)
                    }
                }
                2 -> {

                }
            }
        }



        binding.searchButton.setOnClickListener {
            getListOfSearch()
        }

        binding.searchEditText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                getListOfSearch()
                true
            } else {
                false
            }
        }

        binding.searchRecyclerView.adapter = adapter

    }

    private fun getListOfSearch() {
        val songName = binding.searchEditText.text.toString()
        Request.getSongs(songName) {
            adapter.submitList(it)
        }
    }


    private fun getYoutubeDownloader(youtubeLink: String, onResponse: (String) -> Unit) {
        object : YouTubeExtractor(requireContext()) {
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

    private fun playSong(list: ArrayList<Item>, position: Int) {
        if (!player.isPlaying) {
            val currentVideoLink = BASEURL + list[position].id?.videoId
            getYoutubeDownloader(currentVideoLink) { audioUrl ->
                if (list[position].isResumed == true) {
                    player.start()
                }
                player.setAudioStreamType(AudioManager.STREAM_MUSIC)
                if (player.isPlaying == false && list[position].isResumed == false) {
                    try {
                        player.setDataSource(audioUrl)
                        player.prepare()
                        player.start()
                        if (player.isPlaying) {
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
            player.pause()
            adapter.submitList(list)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is PassDataInterface) {
            passDataInterface = context
        }
    }
}