package com.example.starwarsencyclopedia.planetapi

import com.example.starwarsencyclopedia.BaseTest
import com.example.starwarsencyclopedia.model.network.planetapi.PlanetApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PlanetApiServiceTests : BaseTest() {

    private lateinit var service: PlanetApiService

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
            .create(PlanetApiService::class.java)
    }

    @Test
    fun planet_api_service_works() {
        enqueue("fake_planet_call.json")
        runBlocking {
            val apiResponse = service.getPlanet(1)

            assertNotNull(apiResponse)
            assertEquals(
                "The name did not match", "Tatooine", apiResponse.name
            )
        }
    }
}