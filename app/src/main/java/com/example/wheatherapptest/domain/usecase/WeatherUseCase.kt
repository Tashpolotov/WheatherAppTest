package com.example.wheatherapptest.domain.usecase

import com.example.wheatherapptest.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend fun getWeather(country:String) = repository.getWeather(country)


}