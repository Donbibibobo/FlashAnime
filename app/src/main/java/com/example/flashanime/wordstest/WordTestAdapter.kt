package com.example.flashanime.wordstest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.data.PlayWords
import com.example.flashanime.databinding.ItemWordcardBinding

class WordTestAdapter(val context: Context, val list: List<PlayWords>, viewModel: WordTestViewModel): RecyclerView.Adapter<WordTestAdapter.WordTestViewHolder>() {
    inner class WordTestViewHolder(val binding: ItemWordcardBinding)
        : RecyclerView.ViewHolder(binding.root)

    val viewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordTestViewHolder {
        return WordTestViewHolder(ItemWordcardBinding.inflate(LayoutInflater.from(context)
        , parent, false))
    }

    override fun onBindViewHolder(holder: WordTestViewHolder, position: Int) {
        holder.binding.episodeWords.text = list[position].word

        holder.binding.constraint.setOnClickListener {
            Log.i("touchhh","called")
            viewModel.getWordInfoWordsTest(list[position].word)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}