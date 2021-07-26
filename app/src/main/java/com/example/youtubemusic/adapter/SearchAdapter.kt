package com.example.youtubemusic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.youtubemusic.databinding.CardItemSongsBinding
import com.example.youtubemusic.databinding.NewCardItemBinding
import com.example.youtubemusic.models.Item

class SearchAdapter(val onItemClickListener: (Int,Int) -> Unit) : ListAdapter<Item,SearchViewHolder>(DiffCallBack()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = NewCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }


    class DiffCallBack : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) =
            oldItem._id == newItem._id


        override fun areContentsTheSame(oldItem: Item, newItem: Item) =
            oldItem == newItem

    }



}