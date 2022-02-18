package com.example.starwarsencyclopedia.network.planetapi

import com.squareup.moshi.Json

data class Planet(
    val name: String,
    @Json(name = "rotation_period") val rotationPeriod: String,
    @Json(name = "orbital_period") val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    @Json(name = "surface_water") val surfaceWater: String,
    val population: String
)
