package com.example.starwarsencyclopedia.network.starshipapi

import com.example.starwarsencyclopedia.network.util.ApiUtil
import retrofit2.http.GET
import retrofit2.http.Path

interface StarshipApiService{
    @GET("starships/{id}")
    suspend fun getStarship(
        @Path("id") starshipNum: Int
    ): Starship
}

object StarshipApi {
    val retrofitService: StarshipApiService by lazy {
        ApiUtil.retrofit.create(StarshipApiService::class.java)
    }
}