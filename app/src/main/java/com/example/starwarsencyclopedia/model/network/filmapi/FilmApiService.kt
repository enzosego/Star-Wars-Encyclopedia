package com.example.starwarsencyclopedia.model.network.filmapi

import com.example.starwarsencyclopedia.model.network.util.ApiUtil
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmApiService{
    @GET("films/{id}/")
    suspend fun getFilm(
        @Path("id") filmNum: Int
    ): Film
}

object FilmApi {
    val retrofitService: FilmApiService by lazy {
        ApiUtil.retrofit.create(FilmApiService::class.java)
    }
}
