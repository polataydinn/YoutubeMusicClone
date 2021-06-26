package com.example.youtubemusic.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.youtubemusic.databinding.CardItemSongsBinding
import com.example.youtubemusic.models.Item

class SearchViewHolder(private val binding: CardItemSongsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item, onItemClickListener: (Int,Int) -> Unit){
        binding.songTitle.text = item.snippet?.title
        Glide.with(binding.songThumbNail).load(item.snippet?.thumbnails?.high?.url)
            .diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.songThumbNail)

        binding.apply {
            downloadButton.setOnClickListener {
                onItemClickListener(bindingAdapterPosition,0)
            }
            playButton.setOnClickListener{
                onItemClickListener(bindingAdapterPosition,1)
            }
            addToPlaylistButton.setOnClickListener {
                onItemClickListener(bindingAdapterPosition,2)
            }
        }
    }
}
