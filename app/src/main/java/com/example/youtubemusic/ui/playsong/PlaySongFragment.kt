package com.example.youtubemusic.ui.playsong

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.youtubemusic.MainActivity
import com.example.youtubemusic.R
import com.example.youtubemusic.databinding.FragmentPlaySongBinding
import com.example.youtubemusic.ui.base.BaseFragment

class PlaySongFragment : BaseFragment() {

    private var _binding: FragmentPlaySongBinding? = null
    private val binding get() = _binding!!
    private val BASEURL = "https://www.youtube.com/watch?v="

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaySongBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.songPlayButton.setImageResource(R.drawable.ic_baseline_pause_24_pink)


        binding.totalSongTime.text = (activity as MainActivity).songLenght
        binding.songPlayButton.setOnClickListener {
            if ((activity as MainActivity).player.isPlaying) {
                binding.songPlayButton.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                (activity as MainActivity).player.pause()
            } else {
                binding.songPlayButton.setImageResource(R.drawable.ic_baseline_pause_24_pink)
                (activity as MainActivity).player.start()
            }
        }

        binding.replaySong.setOnClickListener {
            if ((activity as MainActivity).player.isLooping) {
                (activity as MainActivity).player.isLooping = false
                binding.replaySong.setImageResource(R.drawable.ic_baseline_repeat_24)
            } else {
                (activity as MainActivity).player.isLooping = true
                binding.replaySong.setImageResource(R.drawable.ic_baseline_repeat_one_24_purple)
            }
        }

        binding.nextSong.setOnClickListener {
            (activity as MainActivity).player.stop()
            (activity as MainActivity).player.reset()
            (activity as MainActivity).position = (activity as MainActivity).position?.plus(1)
            var youtubeLink =
                BASEURL + (activity as MainActivity).listOfSongs?.get((activity as MainActivity).position!!)?._id?.videoId
            getYoutubeDownloader(youtubeLink, requireContext()) {
                (activity as MainActivity).player.setDataSource(it)
                (activity as MainActivity).player.prepare()
                (activity as MainActivity).player.start()
            }
        }

        binding.previousSong.setOnClickListener {
            (activity as MainActivity).player.stop()
            (activity as MainActivity).player.reset()
            (activity as MainActivity).position = (activity as MainActivity).position?.minus(1)
            var youtubeLink =
                BASEURL + (activity as MainActivity).listOfSongs?.get((activity as MainActivity).position!!)?._id?.videoId
            getYoutubeDownloader(youtubeLink, requireContext()) {
                (activity as MainActivity).player.setDataSource(it)
                (activity as MainActivity).player.prepare()
                (activity as MainActivity).player.start()
            }
        }


//        Glide.with(binding.playSongImage).load(args.songItem.snippet?.thumbnails?.high?.url)
//            .diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.playSongImage)
//
//    }
    }
}