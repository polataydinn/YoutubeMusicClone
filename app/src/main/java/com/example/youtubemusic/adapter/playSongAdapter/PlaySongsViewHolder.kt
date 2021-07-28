package com.example.youtubemusic.adapter.playSongAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.youtubemusic.databinding.CardItemDownloadedSongsBinding
import com.example.youtubemusic.models.DownloadedFile

class PlaySongsViewHolder(private val binding: CardItemDownloadedSongsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(downloadedFile: DownloadedFile?, onItemClickListener: (Int) -> Unit) {
        binding.downloadedSongTitle.text = downloadedFile?.songName
    }
}