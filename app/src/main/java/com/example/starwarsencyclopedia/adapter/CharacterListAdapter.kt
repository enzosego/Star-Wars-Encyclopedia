package com.example.starwarsencyclopedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsencyclopedia.databinding.CharacterViewItemBinding
import com.example.starwarsencyclopedia.network.characterapi.Character


class CharacterListAdapter(private val clickListener: CharacterListener) :
    ListAdapter<Character, CharacterListAdapter.CharacterViewHolder>(DiffCallback) {

    class CharacterViewHolder(
        private var binding: CharacterViewItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: CharacterListener, character: Character) {
            binding.character = character
            binding.clickLister = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.height == newItem.height && oldItem.weight == newItem.weight
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(
            CharacterViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(clickListener, character)
    }
}

class CharacterListener(val clickListener: (character: Character) -> Unit) {
    fun onClick(character: Character) = clickListener(character)
}