package com.example.starwarsencyclopedia.characterapi

import com.example.starwarsencyclopedia.BaseTest
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
    fun character_api_service_works() {
        enqueue("fake_character_page.json")
        runBlocking {
            val apiResponse = service.getFirstPage().results

            assertNotNull(apiResponse)
            assertTrue("The list was empty", apiResponse.isNotEmpty())
            assertEquals(
                "The names did not match", "Luke Skywalker", apiResponse[0].name
            )
        }
    }
}