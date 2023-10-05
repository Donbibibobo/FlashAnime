package com.example.flashanime.collected

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.UserManager
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.databinding.ItemAnimeLargeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CollectedListAdapter(private val click: (AnimeInfo) -> Unit): ListAdapter<AnimeInfo, RecyclerView.ViewHolder>(SeasonProductDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemAnimeLargeBinding.inflate(
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

    class ViewHolder(private val binding: ItemAnimeLargeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(animeInfo: AnimeInfo, click: (AnimeInfo) -> Unit) {

            val db = Firebase.firestore
            val userInfoCollection =
                db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
                    .collection("animeCollection")
            Log.i("loginTest","${UserManager.user?.uid}")



            binding.animeConstrain.setOnClickListener {
                click(animeInfo)
            }
            binding.animeInfo = animeInfo
            binding.executePendingBindings()

            // collected anime
            if (animeInfo.isCollected){
                binding.heatFill.visibility = View.VISIBLE
                binding.heatStroke.visibility = View.GONE
            }else{
                binding.heatStroke.visibility = View.VISIBLE
                binding.heatFill.visibility = View.GONE
            }

            binding.heatStroke.setOnClickListener {
                val userData = hashMapOf(
                    "animeId" to animeInfo.animeId,
                )
                userInfoCollection.document(animeInfo.animeId).set(userData)
            }
            binding.heatFill.setOnClickListener {
                userInfoCollection.document(animeInfo.animeId).delete()
            }
        }
    }

}

private class SeasonProductDiffCallback() : DiffUtil.ItemCallback<AnimeInfo>() {
    override fun areItemsTheSame(oldItem: AnimeInfo, newItem: AnimeInfo): Boolean {
        return oldItem.animeId == newItem.animeId
    }

    override fun areContentsTheSame(oldItem: AnimeInfo, newItem: AnimeInfo): Boolean {
        return oldItem == newItem
    }
}
