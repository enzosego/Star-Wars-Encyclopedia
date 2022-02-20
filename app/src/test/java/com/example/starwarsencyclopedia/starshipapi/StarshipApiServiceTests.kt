package com.example.starwarsencyclopedia.starshipapi

import com.example.starwarsencyclopedia.BaseTest
import com.example.starwarsencyclopedia.model.network.starshipapi.StarshipApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class StarshipApiServiceTests : BaseTest() {

    private lateinit var service: StarshipApiService

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
            .create(StarshipApiService::class.java)
    }

    @Test
    fun starship_api_service_works() {
        enqueue("fake_starship_call.json")
        runBlocking {
            val apiResponse = service.getStarship(1)

            assertNotNull(apiResponse)
            assertEquals(
                "The model name did not match", "T-65 X-wing", apiResponse.model
            )
        }
    }
}