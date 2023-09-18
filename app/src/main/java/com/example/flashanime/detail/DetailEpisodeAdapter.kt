package com.example.flashanime.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.data.Episode
import com.example.flashanime.databinding.ItemEpisodeSelectedBinding
import com.example.flashanime.databinding.ItemEpisodeUnselectedBinding

private const val ITEM_VIEW_TYPE_UNSELECTED = 0x00
private const val ITEM_VIEW_TYPE_SELECTED = 0x01
class DetailEpisodeAdapter(private val click: (Int) -> Unit): ListAdapter<Episode, RecyclerView.ViewHolder>(ProductDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Episode.EpisodeUnSelected -> ITEM_VIEW_TYPE_UNSELECTED
            is Episode.EpisodeSelected -> ITEM_VIEW_TYPE_SELECTED
            else -> throw IllegalArgumentException("DetailEpisodeAdapter getItemViewType unknown.")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_VIEW_TYPE_UNSELECTED -> ViewHolderUnselected(
                ItemEpisodeUnselectedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            ITEM_VIEW_TYPE_SELECTED -> ViewHolderSelected(
                ItemEpisodeSelectedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("DetailEpisodeAdapter onCreateViewHolder unknown.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val episode = getItem(position)
        when(holder){
            is ViewHolderUnselected -> holder.bind(episode as Episode.EpisodeUnSelected, click)
            is ViewHolderSelected -> holder.bind(episode as Episode.EpisodeSelected, click)
            else -> throw IllegalArgumentException("SeasonListAdapter onBindViewHolder holder unknown.")
        }
    }

    class ViewHolderUnselected(private val binding: ItemEpisodeUnselectedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Episode.EpisodeUnSelected, click: (Int) -> Unit) {
            binding.episode = episode.episode
            binding.root.setOnClickListener {
                click(adapterPosition)
            }
            binding.executePendingBindings()
        }
    }
    class ViewHolderSelected(private val binding: ItemEpisodeSelectedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: Episode.EpisodeSelected, click: (Int) -> Unit) {
            binding.episode = episode.episode
            binding.root.setOnClickListener {
                click(adapterPosition)
            }
            binding.executePendingBindings()
        }
    }
}

private class ProductDiffCallback() : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}


