package com.example.starwarsencyclopedia.network.characterapi

import com.squareup.moshi.Json

data class CharacterList(val results: List<Character>)

data class Character(
    val name: String,
    val height: String,
    @Json(name = "mass") val weight: String,
    @Json(name = "hair_color") val hairColor: String,
    @Json(name = "skin_color") val skinColor: String,
    @Json(name = "eye_color") val eyeColor: String,
    @Json(name = "birth_year") val birthYear: String,
    val gender: String
)