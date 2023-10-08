package com.example.flashanime.detail

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.R
import com.example.flashanime.data.PlayWords
import com.example.flashanime.databinding.ItemWordListBinding

class DetailWordListAdapter(private val clickSound: (PlayWords) -> Unit,
                            private val clickWord: (PlayWords) -> Unit,
                            private val context: Context): ListAdapter<PlayWords, RecyclerView.ViewHolder>(DetailProductDiffCallback()) {
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
            ),context
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val animeInfo = getItem(position)
        when(holder){
            is ViewHolder -> {
                val isHighlighted = position == currentPlayingWordPosition
                holder.bind(animeInfo, clickSound, isHighlighted, clickWord)
            }
            else -> throw IllegalArgumentException("SeasonListAdapter onBindViewHolder holder unknown.")
        }
    }

    class ViewHolder(private val binding: ItemWordListBinding, context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        private val colorBg = ContextCompat.getColor(context, R.color.new_bg)
        private val colorSecondary = ContextCompat.getColor(context, R.color.new_secondary)

        private var currentBackgroundColor = colorBg

        fun bind(playWord: PlayWords, clickSound: (PlayWords) -> Unit,
                 isHighlighted: Boolean,  clickWord: (PlayWords) -> Unit) {
            //---- word list
            if (isHighlighted && currentBackgroundColor != colorSecondary) {
                binding.constraint.setBackgroundColor(colorSecondary)
                currentBackgroundColor = colorSecondary
            } else if (!isHighlighted && currentBackgroundColor != colorBg) {
                binding.root.setBackgroundColor(colorBg)
                currentBackgroundColor = colorBg
            }
            //---- word list
            binding.voiceButton.setOnClickListener {
                clickSound(playWord)
            }
            binding.constraint.setOnClickListener {
                clickWord(playWord)
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
