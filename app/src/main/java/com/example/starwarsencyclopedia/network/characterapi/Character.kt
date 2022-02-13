package com.example.starwarsencyclopedia.network.characterapi

import com.squareup.moshi.Json

data class CharacterList(val results: List<Character>)

data class Character(
    @Json(name = "birth_year") val birthYear: String,
    val gender: String,
    val height: String,
    val name: String,
)