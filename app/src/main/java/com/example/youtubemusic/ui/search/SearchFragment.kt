package com.example.youtubemusic.ui.search

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.youtubemusic.MainActivity
import com.example.youtubemusic.R
import com.example.youtubemusic.interfaces.PassDataInterface
import com.example.youtubemusic.models.Item
import com.example.youtubemusic.ui.base.BaseFragment


class SearchFragment : BaseFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SearchAdapter
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
            (activity as MainActivity).position = position
            val list = ArrayList(adapter.currentList)
            val item = list[position]
            passDataInterface.onDataRecieved(item)
            when (case) {
                0 -> {

                }
                1 -> {
                    playSong(list, position,adapter,requireContext())
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
            (activity as MainActivity).listOfSongs = it
            adapter.submitList(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is PassDataInterface) {
            passDataInterface = context
        }
    }
}