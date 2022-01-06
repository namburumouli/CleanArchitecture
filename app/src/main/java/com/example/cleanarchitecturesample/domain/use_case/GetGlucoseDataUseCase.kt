package com.example.cleanarchitecture.domain.use_case

import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.data.remote.dto.toFitGlucoseModel
import com.example.cleanarchitecture.domain.model.FitGlucoseModel
import com.example.cleanarchitecture.domain.repository.FitGlucoseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGlucoseDataUseCase @Inject constructor(
    private  val repository: FitGlucoseRepository
) {
    operator fun invoke(): Flow<Resource<List<FitGlucoseModel>>> = flow {
        try{
            emit(Resource.Loading<List<FitGlucoseModel>>())
            val glucoseDate = repository.getGlucoseData().map { it.toFitGlucoseModel() }
            emit(Resource.Success<List<FitGlucoseModel>>(glucoseDate))

        }catch (e: HttpException){
            // emit error

        }catch (e :IOException){
            // emit error
        }
    }.flowOn(Dispatchers.IO)
}