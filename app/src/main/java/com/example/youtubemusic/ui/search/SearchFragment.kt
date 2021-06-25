package com.example.youtubemusic.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import com.example.youtubemusic.adapter.SearchAdapter
import com.example.youtubemusic.databinding.FragmentSearchBinding
import com.example.youtubemusic.service.Request

class SearchFragment : Fragment() {


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
        adapter = SearchAdapter {}
        binding.searchButton.setOnClickListener {
            val songName = binding.searchEditText.text.toString()

            Request.getSongs (songName,{
                adapter.submitList(it)
            })
        }

        binding.searchRecyclerView.adapter = adapter

    }
}