package com.example.starwarsencyclopedia.model.network.filmapi

import com.squareup.moshi.Json

data class Film(
    val title: String,
    @Json(name = "episode_id") val episodeId: String,
    @Json(name = "opening_crawl") val openingCrawl: String,
    val director: String,
    val producer: String,
    @Json(name = "release_date") val releaseDate: String
)