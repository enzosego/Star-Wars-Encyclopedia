package com.example.starwarsencyclopedia.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsencyclopedia.network.characterapi.CharacterApi
import com.example.starwarsencyclopedia.network.characterapi.Character
import kotlinx.coroutines.launch
import java.lang.Exception

enum class CharacterApiStatus {LOADING, ERROR, DONE}

class CharacterViewModel(
    fakeList: List<Character> = mutableListOf()
): ViewModel() {

    private val _status = MutableLiveData<CharacterApiStatus>()
    val status: LiveData<CharacterApiStatus> = _status

    private val _characterList = MutableLiveData<List<Character>>()

    private val _currentPage = MutableLiveData<List<Character>>()
    val currentPage: LiveData<List<Character>> = _currentPage

    private val _currentPageNum = MutableLiveData(0)
    val currentPageNum: LiveData<Int> = _currentPageNum

    private val _currentCharacter = MutableLiveData<Character>()
    val currentCharacter: LiveData<Character> = _currentCharacter

    val isDescriptionDisplayed = MutableLiveData(false)

    private val _isUserSearching = MutableLiveData(false)
    val isUserSearching: LiveData<Boolean> = _isUserSearching

    init {
        if (fakeList.isEmpty())
            getCharacters()
        else {
            _characterList.value = fakeList
            refreshPage()
        }
    }

    private fun getCharacters() {
        viewModelScope.launch {
            _status.value = CharacterApiStatus.LOADING
            try {
                val newList = mutableListOf<Character>()
                newList.addAll(
                    CharacterApi.retrofitService.getFirstPage().results
                )
                for (index in 2..9) {
                    newList.addAll(
                        CharacterApi.retrofitService.getDesiredPage(index).results
                    )
                }

                _characterList.value = newList
                refreshPage()
                _status.value = CharacterApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CharacterApiStatus.ERROR
                _characterList.value = listOf()
            }
        }
    }

    fun refreshPage() {
        updateCurrentPage(_currentPageNum.value!!)
    }

    private fun updateCurrentPage(pageNum: Int) {
        val newList = mutableListOf<Character>()
        for (charNum in pageNum*10..pageNum*10+9) {
            if (charNum > 81)
                break
            val newChar = _characterList.value!![charNum]
            newList.add(newChar)
        }

        _currentPage.value = newList
        _currentPageNum.value = pageNum
    }

    fun pageUp() {
        _currentPageNum.value = _currentPageNum.value!!.inc()
        refreshPage()
    }

    fun pageDown() {
        _currentPageNum.value = _currentPageNum.value!!.dec()
        refreshPage()
    }

    fun filterCharacters(input: String?) {
        if (input.isNullOrBlank())
            return
        if (!isUserSearching.value!!)
            switchSearchingStatus(true)

        val filteredList = _characterList.value!!
            .filter { (name) ->
                val splitName = name.split(" ")
                if (splitName.size == 1)
                    splitName[0].startsWith(input, true)
                else
                    splitName[0].startsWith(input, true)
                            || splitName[1].startsWith(input, true)
            }

        _currentPage.value = filteredList
    }

    fun switchSearchingStatus(newValue: Boolean) {
        _isUserSearching.value = newValue
        Log.d("debug", "GOT HERE!")
    }

    fun switchDescriptionDisplayStatus() {
        isDescriptionDisplayed.value = !isDescriptionDisplayed.value!!
    }

    fun onCharacterClicked(character: Character) {
        _currentCharacter.value = character
        switchDescriptionDisplayStatus()
        switchSearchingStatus(false)
        refreshPage()
    }
}