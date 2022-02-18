package com.example.starwarsencyclopedia.network.starshipapi

import com.squareup.moshi.Json

data class Starship(
    val name: String,
    val model: String,
    val manufacturer: String,
    @Json(name = "cost_in_credits") val cost: String,
    val length: String,
    @Json(name = "max_atmosphering_speed") val maxSpeed: String,
    val crew: String,
    val passengers: String,
    @Json(name = "cargo_capacity") val cargoCapacity: String,
    val consumables: String,
    @Json(name = "hyperdrive_rating") val hyperdriveRating: String,
    @Json(name = "starship_class") val starshipClass: String,
)
