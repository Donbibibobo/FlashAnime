package com.example.flashanime.home.viewpage2.week

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.data.WeeklyAnime
import com.example.flashanime.databinding.ItemAnimeWeekBinding

class WeekListAdapter(private val click: (String) -> Unit): ListAdapter<WeeklyAnime, RecyclerView.ViewHolder>(ProductDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemAnimeWeekBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val animeInfo = getItem(position)
        when(holder){
            is ViewHolder -> holder.bind(animeInfo, click)
            else -> throw IllegalArgumentException("SeasonListAdapter onBindViewHolder holder unknown.")
        }
    }

    class ViewHolder(private val binding: ItemAnimeWeekBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weeklyAnime: WeeklyAnime, click: (String) -> Unit) {
            binding.weeklyAnime = weeklyAnime
            binding.executePendingBindings()
            binding.constraint.setOnClickListener {
                click(weeklyAnime.animeId)
            }
        }
    }
}

private class ProductDiffCallback() : DiffUtil.ItemCallback<WeeklyAnime>() {

    override fun areItemsTheSame(oldItem: WeeklyAnime, newItem: WeeklyAnime): Boolean {
        return oldItem.episode == newItem.episode
    }

    override fun areContentsTheSame(oldItem: WeeklyAnime, newItem: WeeklyAnime): Boolean {
        return oldItem == newItem
    }
}
