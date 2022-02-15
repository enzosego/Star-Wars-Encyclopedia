package com.example.starwarsencyclopedia.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsencyclopedia.network.characterapi.CharacterApi
import com.example.starwarsencyclopedia.network.characterapi.Character
import kotlinx.coroutines.launch
import java.lang.Exception

enum class CharacterApiStatus {LOADING, ERROR, DONE}

class CharacterViewModel: ViewModel() {

    private val _status = MutableLiveData<CharacterApiStatus>()
    val status: LiveData<CharacterApiStatus> = _status

    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>> = _characterList

    private val _currentCharacter = MutableLiveData<Character>()
    val currentCharacter: LiveData<Character> = _currentCharacter

    init {
        getCharacterList()
    }

    private fun getCharacterList() {
        viewModelScope.launch {
            _status.value = CharacterApiStatus.LOADING
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
                _status.value = CharacterApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CharacterApiStatus.ERROR
                _characterList.value = listOf()
            }
        }
    }

    fun onCharacterClicked(character: Character) {
        _currentCharacter.value = character
    }
}