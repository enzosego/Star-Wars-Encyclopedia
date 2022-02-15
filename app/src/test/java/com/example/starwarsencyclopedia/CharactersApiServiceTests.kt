package com.example.starwarsencyclopedia

import com.example.starwarsencyclopedia.network.characterapi.CharacterApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CharactersApiServiceTests : BaseTest() {

    private lateinit var service: CharacterApiService

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
            .create(CharacterApiService::class.java)
    }

    @Test
    fun api_service() {
        enqueue("star_wars_characters.json")
        runBlocking {
            val apiResponse = service.getFirstPage().results

            assertNotNull(apiResponse)
            assertTrue("The list was empty", apiResponse.isNotEmpty())
            assertEquals("The names did not match",
                "Luke Skywalker", apiResponse[0].name)
        }
    }
}