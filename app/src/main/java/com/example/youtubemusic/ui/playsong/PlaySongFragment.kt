package com.example.youtubemusic.ui.playsong

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.youtubemusic.R
import com.example.youtubemusic.databinding.FragmentPlaySongBinding
import com.example.youtubemusic.ui.playlists.PlaylistsViewModel

class PlaySongFragment : Fragment() {

    private var _binding: FragmentPlaySongBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<PlaySongFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentPlaySongBinding.inflate(inflater,container,false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.playSongImage).load(args.songItem.snippet?.thumbnails?.high?.url)
            .diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.playSongImage)

    }
}