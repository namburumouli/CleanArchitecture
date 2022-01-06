package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.data.remote.dto.FitGlucoseDto

interface FitGlucoseRepository {

    /**
     * were we define the definition for the repo Function
     */
    suspend fun getGlucoseData() : List<FitGlucoseDto>
}