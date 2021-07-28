package com.example.youtubemusic.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.youtubemusic.adapter.searchAdapter.SearchAdapter
import com.example.youtubemusic.databinding.FragmentSearchBinding
import com.example.youtubemusic.service.Request
import java.util.*
import android.view.KeyEvent
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
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
    private lateinit var fileUri: String
    private lateinit var currentItem: Item

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
                    currentItem = list[position]
                    downloadSong(list[position], requireContext())
                }
                1 -> {
                    playSong(list, position, adapter, requireContext())
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
            it.hideKeyboard()
            getListOfSearch()
        }

        binding.searchEditText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                view.hideKeyboard()
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
            it.forEach {
                it.uuid = UUID.randomUUID().toString()
            }
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

    fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

}