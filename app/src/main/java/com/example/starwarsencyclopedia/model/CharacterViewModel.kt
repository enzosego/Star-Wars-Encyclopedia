package com.example.starwarsencyclopedia.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsencyclopedia.network.characterapi.Character
import com.example.starwarsencyclopedia.network.characterapi.CharacterApi
import kotlinx.coroutines.launch
import java.lang.Exception

class CharacterViewModel: ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>> = _characterList

    init {
        getCharactersFromApi()
    }

    private fun getCharactersFromApi() {
        viewModelScope.launch {
            try {
                val currentList = mutableListOf<Character>()
                currentList.addAll(
                    CharacterApi.retrofitService.getFirstPage().results
                )
                for (page in 2..9) {
                    currentList.addAll(
                        CharacterApi.retrofitService.getDesiredPage(page).results
                    )
                }

                _characterList.value = currentList
                _status.value = currentList[81].name
            } catch (e: Exception) {
                _status.value = "Error: $e"
                _characterList.value = listOf()
            }
        }
    }
}