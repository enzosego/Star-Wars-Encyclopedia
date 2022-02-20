package com.example.starwarsencyclopedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsencyclopedia.model.network.characterapi.CharacterApi
import com.example.starwarsencyclopedia.model.network.characterapi.Character
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ApiStatus {LOADING, ERROR, DONE}

class CharacterViewModel(
    fakeList: List<Character> = mutableListOf()
): ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    private val _characterList = MutableLiveData<List<Character>>()

    private val _currentPage = MutableLiveData<List<Character>>()
    val currentPage: LiveData<List<Character>> = _currentPage

    private val _currentPageNum = MutableLiveData(0)
    val currentPageNum: LiveData<Int> = _currentPageNum

    private val _currentCharacter = MutableLiveData<Character>()
    val currentCharacter: LiveData<Character> = _currentCharacter

    private val _planetId = MutableLiveData<Int>()
    val planetId: LiveData<Int> = _planetId

    private val _filmIdList = MutableLiveData<List<Int>>()
    val filmIdList: LiveData<List<Int>> = _filmIdList

    private val _vehicleIdList = MutableLiveData<List<Int>>()
    val vehicleIdList: LiveData<List<Int>> = _vehicleIdList

    private val _starshipIdList = MutableLiveData<List<Int>>()
    val starshipIdList: LiveData<List<Int>> = _starshipIdList

    private val _currentIds = MutableLiveData<Map<String, Int>>()
    val currentIds: LiveData<Map<String, Int>> = _currentIds

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
            _status.value = ApiStatus.LOADING
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
                _status.value = ApiStatus.DONE
            }catch (e: Exception) {
                _status.value = ApiStatus.ERROR
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

        _currentPage.postValue(filteredList)
    }

    fun switchSearchingStatus(newValue: Boolean) {
        _isUserSearching.postValue(newValue)
    }

    fun switchDescriptionDisplayStatus() {
        isDescriptionDisplayed.value = !isDescriptionDisplayed.value!!
    }

    fun onCharacterClicked(character: Character) {
        _currentCharacter.value = character
        getAllIds()
        switchDescriptionDisplayStatus()
        switchSearchingStatus(false)
        refreshPage()
    }

    fun onFilmClicked(id: Int) {
        val newIdMap = mutableMapOf<String, Int>()
        if (!_currentIds.value.isNullOrEmpty())
            newIdMap.putAll(_currentIds.value!!)
        newIdMap["film"] = id

        _currentIds.value = newIdMap
    }

    fun onVehicleClicked(id: Int) {
        val newIdMap = mutableMapOf<String, Int>()
        if (!_currentIds.value.isNullOrEmpty())
            newIdMap.putAll(_currentIds.value!!)
        newIdMap["vehicle"] = id

        _currentIds.value = newIdMap
    }

    fun onStarshipClicked(id: Int) {
        val newIdMap = mutableMapOf<String, Int>()
        if (!_currentIds.value.isNullOrEmpty())
            newIdMap.putAll(_currentIds.value!!)
        newIdMap["starship"] = id

        _currentIds.value = newIdMap
    }

    private fun getAllIds() {
        with(_currentCharacter.value!!) {
            val newFilmList = mutableListOf<Int>()
            for (film in films)
                newFilmList.add(getApiId(film))

            val newVehicleList = mutableListOf<Int>()
            if (vehicles.isNotEmpty())
                for (vehicle in vehicles)
                    newVehicleList.add(getApiId(vehicle))

            val newStarshipList = mutableListOf<Int>()
            if (starships.isNotEmpty())
                for (starship in starships)
                    newStarshipList.add(getApiId(starship))

            _planetId.value = getApiId(homeworld)
            _filmIdList.value = newFilmList
            _vehicleIdList.value = newVehicleList
            _starshipIdList.value = newStarshipList
        }
    }

    private fun getApiId(apiText: String): Int =
        apiText.filter { it.isDigit() }.toInt()
}