package com.example.starwarsencyclopedia.model.network.vehicleapi

import com.example.starwarsencyclopedia.model.network.util.ApiUtil
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleApiService{
    @GET("vehicles/{id}/")
    suspend fun getVehicle(
        @Path("id") vehicleNum: Int
    ): Vehicle
}

object VehicleApi {
    val retrofitService: VehicleApiService by lazy {
        ApiUtil.retrofit.create(VehicleApiService::class.java)
    }
}
