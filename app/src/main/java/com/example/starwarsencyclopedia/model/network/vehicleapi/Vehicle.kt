package com.example.starwarsencyclopedia.model.network.vehicleapi

import com.squareup.moshi.Json

data class Vehicle(
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
    @Json(name = "vehicle_class") val vehicleClass: String,
)
