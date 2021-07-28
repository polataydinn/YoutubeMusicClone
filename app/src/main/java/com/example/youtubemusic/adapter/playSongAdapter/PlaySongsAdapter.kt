package com.example.youtubemusic.adapter.playSongAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.youtubemusic.databinding.CardItemDownloadedSongsBinding
import com.example.youtubemusic.models.DownloadedFile

class PlaySongsAdapter(val onItemClickListener: (Int) -> Unit) : ListAdapter<DownloadedFile, PlaySongsViewHolder>(
    DiffCallBack()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaySongsViewHolder {
        val binding = CardItemDownloadedSongsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlaySongsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaySongsViewHolder, position: Int) {
        holder.bind(getItem(position),onItemClickListener)
    }

    class DiffCallBack : DiffUtil.ItemCallback<DownloadedFile>() {

        override fun areItemsTheSame(oldItem: DownloadedFile, newItem: DownloadedFile) =
            oldItem.uuid == newItem.uuid

        override fun areContentsTheSame(oldItem: DownloadedFile, newItem: DownloadedFile) =
            oldItem == newItem

    }
}