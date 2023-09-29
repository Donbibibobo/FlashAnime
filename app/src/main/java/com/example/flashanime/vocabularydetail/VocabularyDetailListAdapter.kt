package com.example.flashanime.vocabularydetail

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.data.PlayWords
import com.example.flashanime.databinding.ItemWordListBinding
import com.example.flashanime.databinding.ItemWordListTestBinding

class VocabularyDetailListAdapter(private val clickWord: (PlayWords) -> Unit): ListAdapter<PlayWords, RecyclerView.ViewHolder>(DetailProductDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemWordListTestBinding.inflate(
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
                holder.bind(animeInfo, clickWord, position)
            }
            else -> throw IllegalArgumentException("SeasonListAdapter onBindViewHolder holder unknown.")
        }
    }

    class ViewHolder(private val binding: ItemWordListTestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playWord: PlayWords, clickWord: (PlayWords) -> Unit, position: Int) {
            binding.constraint.setOnClickListener {
                clickWord(playWord)
            }
            binding.listNumber.text = (position+1).toString()
            binding.playWord = playWord

            if (playWord.isCollected){
                binding.collectdSave.visibility = View.VISIBLE
            } else {
                binding.collectdSave.visibility = View.GONE
            }


            binding.executePendingBindings()
        }
    }

}

private class DetailProductDiffCallback() : DiffUtil.ItemCallback<PlayWords>() {
    override fun areItemsTheSame(oldItem: PlayWords, newItem: PlayWords): Boolean {
        return oldItem.word == newItem.word
    }

    override fun areContentsTheSame(oldItem: PlayWords, newItem: PlayWords): Boolean {
        return false
    }
}
