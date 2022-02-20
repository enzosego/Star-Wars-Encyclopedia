package com.example.starwarsencyclopedia

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsencyclopedia.adapter.CharacterListAdapter
import com.example.starwarsencyclopedia.adapter.FilmListAdapter
import com.example.starwarsencyclopedia.adapter.StarshipListAdapter
import com.example.starwarsencyclopedia.adapter.VehicleListAdapter
import com.example.starwarsencyclopedia.model.network.characterapi.Character
import com.example.starwarsencyclopedia.viewmodel.ApiStatus
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("characterListData")
fun bindCharacterRecyclerView(recyclerView: RecyclerView, data: List<Character>?) {
    val adapter = recyclerView.adapter as CharacterListAdapter
    adapter.submitList(data)
}

@BindingAdapter("filmListData")
fun bindFilmRecyclerView(recyclerView: RecyclerView, data: List<Int>?) {
    val adapter = recyclerView.adapter as FilmListAdapter
    adapter.submitList(data)
}

@BindingAdapter("vehicleListData")
fun bindVehicleRecyclerView(recyclerView: RecyclerView, data: List<Int>?) {
    val adapter = recyclerView.adapter as VehicleListAdapter
    adapter.submitList(data)
}

@BindingAdapter("starshipListData")
fun bindStarshipRecyclerView(recyclerView: RecyclerView, data: List<Int>?) {
    val adapter = recyclerView.adapter as StarshipListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when(status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("layoutVisibility")
fun layoutVisibility(layout: View, status: ApiStatus) {
    when (status) {
        ApiStatus.LOADING -> {
            layout.visibility = View.GONE
        }
        ApiStatus.ERROR -> {
            layout.visibility = View.GONE
        }
        ApiStatus.DONE -> {
            layout.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter(value = ["status", "isUserSearching"])
fun bottomMenuVisibility(bottomMenu: ConstraintLayout, status: ApiStatus?, isUserSearching: Boolean) {
    when(status) {
        ApiStatus.LOADING -> {
            bottomMenu.visibility = View.GONE
        }
        ApiStatus.DONE -> {
            if (!isUserSearching) {
                bottomMenu.visibility = View.VISIBLE
            } else {
                bottomMenu.visibility = View.GONE
            }
        }
        ApiStatus.ERROR -> {
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