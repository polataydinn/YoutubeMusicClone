package com.example.youtubemusic.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.youtubemusic.constant.Const
import com.example.youtubemusic.databinding.CardItemSongsBinding
import com.example.youtubemusic.models.Item


class SearchViewHolder(private val binding: CardItemSongsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item, onItemClickListener: (Int, Int) -> Unit) {
        binding.title.text = item.snippet?.title
        Glide.with(binding.songThumbnail).load(item.snippet?.thumbnails?.high?.url)
            .diskCacheStrategy(DiskCacheStrategy.DATA).into(binding.songThumbnail)

        binding.apply {
            download.setOnClickListener {
                onItemClickListener(bindingAdapterPosition, 0)
            }
            play.setOnClickListener {
                onItemClickListener(bindingAdapterPosition, 1)
            }
            addPlaylist.setOnClickListener {
                onItemClickListener(bindingAdapterPosition, 2)
            }
        }
        binding.songThumbnail.animation = Const.rotationAnimation
        if (item.isRotating){
            binding.songThumbnail.startAnimation(Const.rotationAnimation)
        } else {
            binding.songThumbnail.clearAnimation()
        }
    }
}