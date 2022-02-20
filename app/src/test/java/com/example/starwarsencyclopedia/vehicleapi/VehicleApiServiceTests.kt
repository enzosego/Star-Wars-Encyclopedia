package com.example.starwarsencyclopedia.vehicleapi

import com.example.starwarsencyclopedia.BaseTest
import com.example.starwarsencyclopedia.model.network.vehicleapi.VehicleApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class VehicleApiServiceTests : BaseTest() {

    private lateinit var service: VehicleApiService

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
            .create(VehicleApiService::class.java)
    }

    @Test
    fun planet_api_service_works() {
        enqueue("fake_vehicle_call.json")
        runBlocking {
            val apiResponse = service.getVehicle(1)

            assertNotNull(apiResponse)
            assertEquals(
                "The manufacturer name did not match", "Incom corporation", apiResponse.manufacturer
            )
        }
    }
}