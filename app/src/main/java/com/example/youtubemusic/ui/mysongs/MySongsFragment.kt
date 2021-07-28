package com.example.youtubemusic.ui.mysongs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.youtubemusic.R
import com.example.youtubemusic.adapter.playSongAdapter.PlaySongsAdapter
import com.example.youtubemusic.databinding.FragmentMySongsBinding

class MySongsFragment : Fragment() {

    private var _binding: FragmentMySongsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mySongsViewModel: MySongsViewModel
    private lateinit var adapter : PlaySongsAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMySongsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mySongsViewModel = ViewModelProvider(this).get(MySongsViewModel::class.java)

        mySongsViewModel.apply {
            getDownloadedSongs()
            songs?.observe(viewLifecycleOwner){downloadedSongs ->
                adapter.submitList(downloadedSongs)
            }
        }

        adapter = PlaySongsAdapter {}
        binding.downloadedSongs.adapter = adapter
    }
}