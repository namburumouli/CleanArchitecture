package com.example.cleanarchitecturesample.di

import com.example.cleanarchitecture.common.Constants
import com.example.cleanarchitecture.data.remote.dto.WebServiceAPI
import com.example.cleanarchitecture.data.repository.FitGlucoseImpl
import com.example.cleanarchitecture.domain.repository.FitGlucoseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(HttpLoggingInterceptor())
        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideWebServiceApi():WebServiceAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebServiceAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideFitGlucoseRepository(api: WebServiceAPI):FitGlucoseRepository{
        return FitGlucoseImpl(api)
    }
}


