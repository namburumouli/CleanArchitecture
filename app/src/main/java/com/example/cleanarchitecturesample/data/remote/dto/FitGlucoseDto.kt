package com.example.cleanarchitecture.data.remote.dto

import com.example.cleanarchitecture.domain.model.FitGlucoseModel

/**
 *
 */

data class FitGlucoseDto(
    val createdAt: String,
    val glucoseLevel: Int,
    val patientID: String,
    val name: String
)

/**
 * to Map data transfer object to required presentation Model
 */
fun FitGlucoseDto.toFitGlucoseModel(): FitGlucoseModel{
    return FitGlucoseModel(
        glucoseLevel = glucoseLevel,
        name = name
    )
}
