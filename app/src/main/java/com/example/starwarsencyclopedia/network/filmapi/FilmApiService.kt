package com.example.starwarsencyclopedia.network.filmapi

import com.example.starwarsencyclopedia.network.util.ApiUtil
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmApiService{
    @GET("films/{id}")
    suspend fun getFilm(
        @Path("id") filmNumber: Int
    ): Film
}

object FilmApi {
    val retrofitService: FilmApiService by lazy {
        ApiUtil.retrofit.create(FilmApiService::class.java)
    }
}
