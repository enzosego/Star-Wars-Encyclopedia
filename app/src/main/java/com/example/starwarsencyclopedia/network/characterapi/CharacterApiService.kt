package com.example.starwarsencyclopedia.network.characterapi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://swapi.dev/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

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
        retrofit.create(CharacterApiService::class.java)
    }
}