package com.example.cleanarchitecturesample.presentation.fit_glucosedata

import com.example.cleanarchitecture.domain.model.FitGlucoseModel

data class GlucoseListState(
    val isLoading: Boolean = false,
    val glucoseData: List<FitGlucoseModel> = emptyList(),
    val error: String = ""

)
