package com.example.wheatherapptest.data.di.network

import com.example.wheatherapptest.data.remote.WeatherApiService
import com.example.wheatherapptest.data.repository.WeatherRepositoryImpl
import com.example.wheatherapptest.domain.repository.WeatherRepository
import com.example.wheatherapptest.domain.usecase.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):WeatherApiService{
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(apiService: WeatherApiService):WeatherRepository{
        return WeatherRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: WeatherRepository):WeatherUseCase{
        return WeatherUseCase(repository)
    }
}