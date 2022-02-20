package com.example.starwarsencyclopedia.model.network.characterapi

import com.example.starwarsencyclopedia.model.network.util.ApiUtil
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {
    @GET("people/")
    suspend fun getFirstPage(): CharacterList

    @GET("people/")
    suspend fun getDesiredPage(
        @Query("page") pageNum: Int
    ): CharacterList
}

object CharacterApi {
    val retrofitService: CharacterApiService by lazy {
        ApiUtil.retrofit.create(CharacterApiService::class.java)
    }
}