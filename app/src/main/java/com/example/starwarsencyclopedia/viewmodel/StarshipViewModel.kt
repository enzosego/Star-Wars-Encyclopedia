package com.example.starwarsencyclopedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsencyclopedia.model.network.starshipapi.Starship
import com.example.starwarsencyclopedia.model.network.starshipapi.StarshipApi
import kotlinx.coroutines.launch
import java.lang.Exception

class StarshipViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _currentStarship = MutableLiveData<Starship>()
    val currentStarship: LiveData<Starship> = _currentStarship

    fun getStarship(id: Int) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val newStarship = StarshipApi.retrofitService.getStarship(id)
                _currentStarship.value = newStarship
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }
}