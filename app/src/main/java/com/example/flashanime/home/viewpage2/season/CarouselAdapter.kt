package com.example.flashanime.home.viewpage2.season

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.UserManager
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.databinding.ItemCarouselLayoutBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CarouselAdapter(private val click: (AnimeInfo) -> Unit): ListAdapter<AnimeInfo, RecyclerView.ViewHolder>(
    HotDiffCallback()
) {


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
            binding.carouselItemContainer.setOnClickListener {
                click(animeInfo)
            }

            val db = Firebase.firestore
            val userInfoCollection =
                db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
                    .collection("animeCollection")
            Log.i("loginTest","${UserManager.user?.uid}")

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

            // login
            if (UserManager.user?.uid == null){
                binding.heatFill.visibility = View.GONE
                binding.heatStroke.visibility = View.GONE
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
