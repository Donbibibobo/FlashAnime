package com.example.flashanime.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.databinding.ItemAnimeMediumBinding

class WatchHistoryListAdapter(private val click: (AnimeInfo) -> Unit): ListAdapter<AnimeInfo, RecyclerView.ViewHolder>(SeasonProductDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemAnimeMediumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val animeInfo = getItem(position)
        when(holder){
            is ViewHolder -> holder.bind(animeInfo,click)
            else -> throw IllegalArgumentException("SeasonListAdapter onBindViewHolder holder unknown.")
        }
    }

    class ViewHolder(private val binding: ItemAnimeMediumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(animeInfo: AnimeInfo, click: (AnimeInfo) -> Unit) {
            binding.animeConstrain.setOnClickListener {
                click(animeInfo)
            }
            binding.animeInfo = animeInfo
            binding.executePendingBindings()
        }
    }
}

private class SeasonProductDiffCallback() : DiffUtil.ItemCallback<AnimeInfo>() {
    override fun areItemsTheSame(oldItem: AnimeInfo, newItem: AnimeInfo): Boolean {
        return oldItem.videosId == newItem.videosId
    }

    override fun areContentsTheSame(oldItem: AnimeInfo, newItem: AnimeInfo): Boolean {
        return oldItem == newItem
    }
}
