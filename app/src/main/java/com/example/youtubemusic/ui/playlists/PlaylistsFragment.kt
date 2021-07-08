package com.example.youtubemusic.ui.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.youtubemusic.R
import com.example.youtubemusic.ui.base.BaseFragment

class PlaylistsFragment : BaseFragment() {

    private lateinit var playlistsViewModel: PlaylistsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        playlistsViewModel = ViewModelProvider(this).get(PlaylistsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_playlists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}