package com.example.starwarsencyclopedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsencyclopedia.model.network.filmapi.Film
import com.example.starwarsencyclopedia.model.network.filmapi.FilmApi
import kotlinx.coroutines.launch
import java.lang.Exception

class FilmViewModel() : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _currentFilm = MutableLiveData<Film>()
    val currentFilm: LiveData<Film> = _currentFilm

    fun getFilm(id: Int) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val newFilm = FilmApi.retrofitService.getFilm(id)
                _currentFilm.value = newFilm
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }
}