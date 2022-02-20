package com.example.starwarsencyclopedia.filmapi

import com.example.starwarsencyclopedia.BaseTest
import com.example.starwarsencyclopedia.model.network.filmapi.FilmApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FilmApiServiceTests : BaseTest() {

    private lateinit var service: FilmApiService

    @Before
    fun setup() {
        val url = mockWebServer.url("/")
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                ))
            .build()
            .create(FilmApiService::class.java)
    }

    @Test
    fun film_api_service_works() {
        enqueue("fake_film_call.json")
        runBlocking {
            val apiResponse = service.getFilm(1)

            assertNotNull(apiResponse)
            assertEquals(
                "The title did not match", "A New Hope", apiResponse.title
            )
        }
    }
}