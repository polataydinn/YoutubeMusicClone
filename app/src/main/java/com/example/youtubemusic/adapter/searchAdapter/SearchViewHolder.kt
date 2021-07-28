package com.example.youtubemusic.adapter.searchAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.youtubemusic.models.Item
import com.example.youtubemusic.R
import com.example.youtubemusic.databinding.NewCardItemBinding


class SearchViewHolder(private val binding: NewCardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item, onItemClickListener: (Int, Int) -> Unit) {
        binding.searchTitle.text = item.snippet?.title
        Glide.with(binding.searchImage).load(item.snippet?.thumbnails?.high?.url)
            .diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.searchImage)

        binding.searchSinger.text = item.snippet?.channelTitle

        binding.apply {
            searchDownloadButton.setOnClickListener {
                onItemClickListener(bindingAdapterPosition, 0)
            }
            searchPlayButton.setOnClickListener {

                onItemClickListener(bindingAdapterPosition, 1)
            }
            searchAddPlaylist.setOnClickListener {
                onItemClickListener(bindingAdapterPosition, 2)
            }
        }

        if (item.isPlaying) {
            binding.searchPlayButton.setImageResource(R.drawable.ic_baseline_pause_24)
        } else {
            binding.searchPlayButton.setImageResource(R.drawable.ic_baseline_play_arrow_24_white)
        }
    }
}