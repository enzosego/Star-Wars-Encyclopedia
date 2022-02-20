package com.example.starwarsencyclopedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsencyclopedia.adapter.data.DataSource
import com.example.starwarsencyclopedia.databinding.StarshipViewItemBinding

class StarshipListAdapter(private val clickListener: StarshipListener) :
    ListAdapter<Int, StarshipListAdapter.StarshipViewHolder>(DiffCallback) {

    class StarshipViewHolder(
        private var binding: StarshipViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: StarshipListener, starshipId: Int, starshipName: String) {
            binding.clickListener = clickListener
            binding.starshipId = starshipId
            binding.starshipName = starshipName
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(
            oldItem: Int, newItem: Int): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: Int, newItem: Int): Boolean = false
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): StarshipViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StarshipViewHolder(
            StarshipViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StarshipViewHolder, position: Int) {
        val starshipId = getItem(position)
        val starshipName = DataSource.starshipNames[starshipId]
        holder.bind(clickListener, starshipId, starshipName!!)
    }
}

class StarshipListener(val clickListener: (starshipId: Int) -> Unit) {
    fun onClick(starshipId: Int) = clickListener(starshipId)
}
