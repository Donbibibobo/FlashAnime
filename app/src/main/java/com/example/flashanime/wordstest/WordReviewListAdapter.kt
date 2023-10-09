package com.example.flashanime.wordstest

import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.R
import com.example.flashanime.UserManager
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.WordsCollection
import com.example.flashanime.databinding.ItemWordTestReviewBinding
import com.example.flashanime.databinding.ItemWordcardBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WordReviewListAdapter(private val clickWord: (WordsCollection) -> Unit): ListAdapter<WordsCollection, RecyclerView.ViewHolder>(DetailProductDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemWordTestReviewBinding.inflate(
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

    class ViewHolder(private val binding: ItemWordTestReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playWord: WordsCollection, clickWord: (WordsCollection) -> Unit, position: Int) {

            val db = Firebase.firestore
            val userCollectedWordsList =
                db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
                    .collection("wordsCollection").document(playWord.word)
            Log.i("loginTest","${UserManager.user?.uid}")


            binding.playWords = playWord
            binding.constraint.setOnClickListener {
                clickWord(playWord)
            }


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


            // change ui with correct or incorrect
            if (!playWord.forReview){
                binding.border.setImageResource(R.drawable.wordcard_border_small_incorrect)
                binding.cardView.cardElevation = 1f
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
