package com.example.flashanime.hot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.databinding.ItemCarouselLayoutBinding

class CarouselAdapter(private val click: (AnimeInfo) -> Unit): ListAdapter<AnimeInfo, RecyclerView.ViewHolder>(HotDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemCarouselLayoutBinding.inflate(
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

    class ViewHolder(private val binding: ItemCarouselLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(animeInfo: AnimeInfo, click: (AnimeInfo) -> Unit) {
            binding.animeInfo = animeInfo
            binding.constraint.setOnClickListener {
                click(animeInfo)
            }
        }
    }

}

private class HotDiffCallback() : DiffUtil.ItemCallback<AnimeInfo>() {
    override fun areItemsTheSame(oldItem: AnimeInfo, newItem: AnimeInfo): Boolean {
        return oldItem.animeId == newItem.animeId
    }

    override fun areContentsTheSame(oldItem: AnimeInfo, newItem: AnimeInfo): Boolean {
        return newItem == oldItem
    }
}
