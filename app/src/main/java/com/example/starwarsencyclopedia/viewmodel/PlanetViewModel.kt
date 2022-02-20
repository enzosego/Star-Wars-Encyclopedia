package com.example.starwarsencyclopedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsencyclopedia.model.network.planetapi.Planet
import com.example.starwarsencyclopedia.model.network.planetapi.PlanetApi
import kotlinx.coroutines.launch
import java.lang.Exception

class PlanetViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _currentPlanet = MutableLiveData<Planet>()
    val currentPlanet: LiveData<Planet> = _currentPlanet

    fun getPlanet(id: Int) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val newPlanet = PlanetApi.retrofitService.getPlanet(id)
                _currentPlanet.value = newPlanet
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }
}