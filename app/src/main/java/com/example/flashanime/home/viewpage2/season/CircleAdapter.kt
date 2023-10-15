package com.example.flashanime.home.viewpage2.season

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.databinding.ItemCircleBinding

class CircleAdapter : ListAdapter<CircleIsSelected, RecyclerView.ViewHolder>(CircleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemCircleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val isSelected = getItem(position)
        when(holder){
            is ViewHolder -> holder.bind(isSelected.isSelected)
            else -> throw IllegalArgumentException("SeasonListAdapter onBindViewHolder holder unknown.")
        }
    }

    class ViewHolder(private val binding: ItemCircleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(isSelected: Boolean) {
            binding.isSelected = isSelected


        }
    }

}

data class CircleIsSelected(
    val position: Int,
    var isSelected: Boolean
)

private class CircleDiffCallback() : DiffUtil.ItemCallback<CircleIsSelected>() {
    override fun areItemsTheSame(oldItem: CircleIsSelected, newItem: CircleIsSelected): Boolean {
        return oldItem.position == newItem.position
    }

    override fun areContentsTheSame(oldItem: CircleIsSelected, newItem: CircleIsSelected): Boolean {
        return newItem.isSelected == oldItem.isSelected
    }
}