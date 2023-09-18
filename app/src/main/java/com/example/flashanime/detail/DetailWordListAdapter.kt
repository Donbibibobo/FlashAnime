package com.example.flashanime.detail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.data.PlayWords
import com.example.flashanime.databinding.ItemWordListBinding

class DetailWordListAdapter(private val click: (PlayWords) -> Unit): ListAdapter<PlayWords, RecyclerView.ViewHolder>(DetailProductDiffCallback()) {
    //----
    private var currentPlayingWordPosition: Int? = null
    fun highlightWordPosition(position: Int) {
        val previousHighlighted = currentPlayingWordPosition
        currentPlayingWordPosition = position
        previousHighlighted?.let { notifyItemChanged(it) }
        notifyItemChanged(position)
    }
    //----
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
            is ViewHolder -> {
                val isHighlighted = position == currentPlayingWordPosition
                holder.bind(animeInfo, click, isHighlighted)
            }
            else -> throw IllegalArgumentException("SeasonListAdapter onBindViewHolder holder unknown.")
        }
    }

    class ViewHolder(private val binding: ItemWordListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playWord: PlayWords, click: (PlayWords) -> Unit, isHighlighted: Boolean) {
            //----
            if (isHighlighted) {
                binding.constraint.setBackgroundColor(Color.GRAY)
            } else {
                binding.root.setBackgroundColor(Color.WHITE)
            }
            //----
            binding.voiceButton.setOnClickListener {
                click(playWord)
            }
            binding.listNumber.text = (adapterPosition+1).toString()
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
