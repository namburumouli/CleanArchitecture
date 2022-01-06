package com.example.cleanarchitecture.data.remote.dto

import retrofit2.http.GET

interface WebServiceAPI {

    @GET("api/v1/data/GoogleFit")
    suspend fun getGlucoseData(): List<FitGlucoseDto>

}