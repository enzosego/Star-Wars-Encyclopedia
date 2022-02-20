package com.example.starwarsencyclopedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsencyclopedia.model.network.vehicleapi.Vehicle
import com.example.starwarsencyclopedia.model.network.vehicleapi.VehicleApi
import kotlinx.coroutines.launch
import java.lang.Exception

class VehicleViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _currentVehicle = MutableLiveData<Vehicle>()
    val currentVehicle: LiveData<Vehicle> = _currentVehicle

    fun getVehicle(id: Int) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val newVehicle = VehicleApi.retrofitService.getVehicle(id)
                _currentVehicle.value = newVehicle
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }
}