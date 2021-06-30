package com.example.youtubemusic.ui.search

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.youtubemusic.adapter.SearchAdapter
import com.example.youtubemusic.databinding.FragmentSearchBinding
import com.example.youtubemusic.service.Request
import org.json.JSONObject
import java.lang.Exception

class SearchFragment : Fragment() {

    private val BASEURL = "https://www.youtube.com/watch?v="
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SearchAdapter

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
            when (case) {
                0 -> {

                }
                1 -> {
                    val list = ArrayList(adapter.currentList)
                    for ((ind, item) in adapter.currentList.withIndex()) {
                        if (ind == position){
                            list[ind] = item.copy(isRotating = !item.isRotating)
                        } else {
                            list[ind] = item.copy(isRotating = false)
                        }
                    }
                    adapter.submitList(list)

                    val currentVideoLink = BASEURL + adapter.currentList[position].id?.videoId
                    getYoutubeDownloader(currentVideoLink)

                }
                2 -> {

                }
            }
        }
        binding.searchButton.setOnClickListener {
            val songName = binding.searchEditText.text.toString()

            Request.getSongs(songName, {
                adapter.submitList(it)
            })
        }

        binding.searchRecyclerView.adapter = adapter

    }

    private fun getYoutubeDownloader(youtubeLink : String) {
        object : YouTubeExtractor(requireContext()){
            override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, videoMeta: VideoMeta?) {

                if (ytFiles == null){
                    return
                }
                else{
                    val itag = 140
                    val downloadUrl = ytFiles.get(itag).url
                    val player = MediaPlayer()
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC)
                    try {
                        player.setDataSource(downloadUrl)
                        player.prepare()
                        player.start()
                    }catch (e : Exception){

                    }
                }
            }

        }.extract(youtubeLink,true,true)
    }


}