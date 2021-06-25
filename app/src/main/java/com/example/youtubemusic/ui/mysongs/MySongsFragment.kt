package com.example.youtubemusic.ui.mysongs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.youtubemusic.R

class MySongsFragment : Fragment() {

    private lateinit var mySongsViewModel: MySongsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mySongsViewModel = ViewModelProvider(this).get(MySongsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_my_songs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}