package com.example.starwarsencyclopedia.network.planetapi

import com.example.starwarsencyclopedia.network.util.ApiUtil
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetApiService{
    @GET("planets/{id}")
    suspend fun getPlanet(
        @Path("id") planetNum: Int
    ): Planet
}

object PlanetApi {
    val retrofitService: PlanetApiService by lazy {
        ApiUtil.retrofit.create(PlanetApiService::class.java)
    }
}
