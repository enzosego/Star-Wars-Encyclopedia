package com.example.starwarsencyclopedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsencyclopedia.adapter.data.DataSource
import com.example.starwarsencyclopedia.databinding.FilmViewItemBinding

class FilmListAdapter(private val clickListener: FilmListener) :
    ListAdapter<Int, FilmListAdapter.FilmViewHolder>(DiffCallback) {

    class FilmViewHolder(
        private var binding: FilmViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: FilmListener, filmId: Int, filmName: String) {
            binding.clickListener = clickListener
            binding.filmId = filmId
            binding.filmName = filmName
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
        parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FilmViewHolder(
            FilmViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val filmId = getItem(position)
        val filmName = DataSource.filmNames[filmId]
        holder.bind(clickListener, filmId, filmName!!)
    }
}

class FilmListener(val clickListener: (filmId: Int) -> Unit) {
    fun onClick(filmId: Int) = clickListener(filmId)
}
