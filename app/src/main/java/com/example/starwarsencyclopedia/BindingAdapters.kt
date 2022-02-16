package com.example.starwarsencyclopedia

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsencyclopedia.adapter.CharacterListAdapter
import com.example.starwarsencyclopedia.model.CharacterApiStatus
import com.example.starwarsencyclopedia.network.characterapi.Character

@BindingAdapter("characterListData")
fun bindCharacterRecyclerView(recyclerView: RecyclerView, data: List<Character>?) {
    val adapter = recyclerView.adapter as CharacterListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: CharacterApiStatus?) {
    when(status) {
        CharacterApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CharacterApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        CharacterApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("backButtonVisibility")
fun backButtonVisibility(button: Button, pageNum: Int) {
    if (pageNum == 0) {
        button.visibility = View.GONE
    } else {
        button.visibility = View.VISIBLE
    }
}

@BindingAdapter("nextButtonVisibility")
fun nextButtonVisibility(button: Button, pageNum: Int) {
    if (pageNum == 8) {
        button.visibility = View.GONE
    } else {
        button.visibility = View.VISIBLE
    }
}
