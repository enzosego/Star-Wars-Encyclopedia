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

class CharacterViewModel(
    fakeList: MutableList<List<Character>> = mutableListOf()
): ViewModel() {

    private val _status = MutableLiveData<CharacterApiStatus>()
    val status: LiveData<CharacterApiStatus> = _status

    private val _pagesList = MutableLiveData<MutableList<List<Character>>>()

    private val _currentPage = MutableLiveData<List<Character>>()
    val currentPage: LiveData<List<Character>> = _currentPage

    private val _currentPageNum = MutableLiveData<Int>()
    val currentPageNum: LiveData<Int> = _currentPageNum

    private val _currentCharacter = MutableLiveData<Character>()
    val currentCharacter: LiveData<Character> = _currentCharacter

    val isDescriptionDisplayed = MutableLiveData(false)

    init {
        if (fakeList.isEmpty())
            getCharacterPages()
        else {
            _pagesList.value = fakeList
            updateCurrentPage(0)
        }
    }

    private fun getCharacterPages() {
        viewModelScope.launch {
            _status.value = CharacterApiStatus.LOADING
            try {
                val newList = mutableListOf<List<Character>>()
                val firstPage = mutableListOf<Character>()
                firstPage.addAll(
                    CharacterApi.retrofitService.getFirstPage().results
                )
                newList.add(firstPage)

                for (index in 2..9) {
                    val newPage = mutableListOf<Character>()
                    newPage.addAll(
                        CharacterApi.retrofitService.getDesiredPage(index).results
                    )
                    newList.add(newPage)
                }

                _pagesList.value = newList
                updateCurrentPage(0)
                _status.value = CharacterApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CharacterApiStatus.ERROR
                _pagesList.value = mutableListOf()
            }
        }
    }

    fun pageUp() {
        _currentPageNum.value = _currentPageNum.value!!.inc()
        updateCurrentPage(_currentPageNum.value!!)
    }

    fun pageDown() {
        _currentPageNum.value = _currentPageNum.value!!.dec()
        updateCurrentPage(_currentPageNum.value!!)
    }

    private fun updateCurrentPage(pageNum: Int) {
        _currentPage.value = _pagesList.value!![pageNum]
        _currentPageNum.value = pageNum
    }

    fun onCharacterClicked(character: Character) {
        _currentCharacter.value = character
        switchDescriptionDisplayStatus(true)
    }

    fun switchDescriptionDisplayStatus(newValue: Boolean) {
        isDescriptionDisplayed.value = newValue
    }
}