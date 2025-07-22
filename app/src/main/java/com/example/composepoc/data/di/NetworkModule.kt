package com.example.composepoc.data.di

import com.example.composepoc.utils.Constant.BASE_URL
import com.example.composepoc.data.netwotk.ApiService
import com.example.composepoc.data.respository.RepositoryImpl
import com.example.composepoc.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    val okkhttp = OkHttpClient.Builder()
        //.certificatePinner(certificatePinner)
        .connectTimeout(30, TimeUnit.SECONDS) // Optional: Set timeouts
        .readTimeout(30, TimeUnit.SECONDS)    // Optional: Set timeouts
        .writeTimeout(30, TimeUnit.SECONDS)   // Optional: Set timeouts
        // Add other configurations like interceptors if needed
        // .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    @Provides
    @Singleton
    fun providerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okkhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providerApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providerRepositoryImpl(apiService: ApiService): Repository {
        return RepositoryImpl(apiService)
    }
}