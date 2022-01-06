package com.example.cleanarchitecturesample.presentation.fit_glucosedata

import android.app.Application
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.domain.model.FitGlucoseModel
import com.example.cleanarchitecture.domain.use_case.GetGlucoseDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FitGlucoseViewModel @Inject constructor(
    private val getGlucoseDataUseCase: GetGlucoseDataUseCase
) : ViewModel() {

    private val state = MutableStateFlow<DataState>(DataState.Empty)
    val uiState: StateFlow<DataState> = state

    sealed class DataState {
        data class Success(
            val listState: GlucoseListState
        ) : DataState()

        object Empty : DataState()

        data class Error(
            val message: String
        ) : DataState()

    }


//    init {
//        getData()
//    }

    fun getData() {
        getGlucoseDataUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
//                   state.value = GlucoseListState(glucoseData = result.data ?: emptyList())
                    state.value = DataState.Success(
                        GlucoseListState(
                            glucoseData = result.data ?: emptyList()
                        )
                    )
//                    Log.i("ViewmodelData", state.value.glucoseData.toString())
//                     Log.e("Data", Date.glucoseData.toString());
                }
                is Resource.Error -> {
                    // error message
                    state.value = DataState.Error("Error")
                }
                is Resource.Loading -> {
                    // loding
                }

            }

        }.launchIn(viewModelScope)
    }
}



