package com.example.flashanime.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.data.PlayWords
import com.example.flashanime.databinding.ItemWordListBinding

class DetailWordListAdapter(private val click: (PlayWords) -> Unit): ListAdapter<PlayWords, RecyclerView.ViewHolder>(DetailProductDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemWordListBinding.inflate(
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

    class ViewHolder(private val binding: ItemWordListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playWord: PlayWords, click: (PlayWords) -> Unit) {
            binding.voiceButton.setOnClickListener {
                click(playWord)
            }
            binding.playWord = playWord
            binding.executePendingBindings()
        }
    }

}

private class DetailProductDiffCallback() : DiffUtil.ItemCallback<PlayWords>() {
    override fun areItemsTheSame(oldItem: PlayWords, newItem: PlayWords): Boolean {
        return oldItem.word == newItem.word
    }

    override fun areContentsTheSame(oldItem: PlayWords, newItem: PlayWords): Boolean {
        return oldItem == newItem
    }
}
