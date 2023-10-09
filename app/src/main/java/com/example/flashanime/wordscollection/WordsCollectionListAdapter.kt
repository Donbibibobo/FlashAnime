package com.example.flashanime.wordscollection

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.UserManager
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.WordsCollection
import com.example.flashanime.databinding.ItemWordTestReviewBigBinding
import com.example.flashanime.databinding.ItemWordTestReviewBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WordsCollectionListAdapter(private val clickWord: (WordsCollection) -> Unit): ListAdapter<WordsCollection, RecyclerView.ViewHolder>(DetailProductDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemWordTestReviewBigBinding.inflate(
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

    class ViewHolder(private val binding: ItemWordTestReviewBigBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playWord: WordsCollection, clickWord: (WordsCollection) -> Unit, position: Int) {

            binding.constraint.setOnClickListener {
                clickWord(playWord)
            }

            val db = Firebase.firestore
            val userCollectedWordsList =
                db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
                    .collection("wordsCollection").document(playWord.word)
            Log.i("loginTest","${UserManager.user?.uid}")


            binding.playWords = playWord


            // remove from collected
            binding.collectdSave.setOnClickListener {
                binding.collectdSave.visibility = View.GONE
                binding.collectdUnSave.visibility = View.VISIBLE

                userCollectedWordsList.delete()
                    .addOnSuccessListener { documentReference ->
                        Log.d("AddFirebase-", "DocumentSnapshot written with ID: $documentReference")
                    }
                    .addOnFailureListener { e ->
                        Log.w("AddFirebase-", "Error adding document", e)
                    }

            }

            // add to collected
            binding.collectdUnSave.setOnClickListener {
                binding.collectdSave.visibility = View.VISIBLE
                binding.collectdUnSave.visibility = View.GONE

                val collectedWords = mapOf(
                    "videoId" to playWord.videoId,
                    "wordsTime" to playWord.wordsTime,
                    "videoTitle" to playWord.videoTitle,
                    "image" to playWord.image,
                    "episodeNum" to playWord.episodeNum,
                    "word" to playWord.word,
                    "isCollected" to true,
                    "episodeId" to playWord.episodeId
                )

                userCollectedWordsList.set(collectedWords)
                    .addOnSuccessListener { documentReference ->
                        Log.d("AddFirebase+", "DocumentSnapshot written with ID: $documentReference")
                    }
                    .addOnFailureListener { e ->
                        Log.w("AddFirebase+", "Error adding document", e)
                    }
            }

        }
    }

}

private class DetailProductDiffCallback() : DiffUtil.ItemCallback<WordsCollection>() {
    override fun areItemsTheSame(oldItem: WordsCollection, newItem: WordsCollection): Boolean {
        return oldItem.word == newItem.word
    }

    override fun areContentsTheSame(oldItem: WordsCollection, newItem: WordsCollection): Boolean {
        return oldItem == newItem
    }
}
