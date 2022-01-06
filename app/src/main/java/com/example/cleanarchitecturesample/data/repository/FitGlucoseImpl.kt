package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.remote.dto.FitGlucoseDto
import com.example.cleanarchitecture.data.remote.dto.WebServiceAPI
import com.example.cleanarchitecture.domain.repository.FitGlucoseRepository
import javax.inject.Inject


class FitGlucoseImpl @Inject constructor(
    private val api: WebServiceAPI
):FitGlucoseRepository{

    /**
     * In this we will Implement the repo
     */
    override suspend fun getGlucoseData(): List<FitGlucoseDto> {
        return api.getGlucoseData()
    }

}