package com.example.starwarsencyclopedia

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsencyclopedia.adapter.CharacterListAdapter
import com.example.starwarsencyclopedia.model.CharacterApiStatus
import com.example.starwarsencyclopedia.network.characterapi.Character
import com.google.android.material.textfield.TextInputEditText

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

@BindingAdapter("searchInputVisibility")
fun searchInputVisibility(inputField: TextInputEditText, status: CharacterApiStatus) {
    when (status) {
        CharacterApiStatus.LOADING -> {
            inputField.visibility = View.GONE
        }
        CharacterApiStatus.ERROR -> {
            inputField.visibility = View.GONE
        }
        CharacterApiStatus.DONE -> {
            inputField.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter(value = ["status", "isUserSearching"])
fun bottomMenuVisibility(bottomMenu: ConstraintLayout, status: CharacterApiStatus?, isUserSearching: Boolean) {
    when(status) {
        CharacterApiStatus.LOADING -> {
            bottomMenu.visibility = View.GONE
        }
        CharacterApiStatus.DONE -> {
            if (!isUserSearching) {
                bottomMenu.visibility = View.VISIBLE
            } else {
                bottomMenu.visibility = View.GONE
            }
        }
        CharacterApiStatus.ERROR -> {
            bottomMenu.visibility = View.GONE
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