package com.example.wheatherapptest.data.repository


import com.example.wheatherapptest.data.mapper.toWeather
import com.example.wheatherapptest.data.remote.WeatherApiService
import com.example.wheatherapptest.domain.repository.WeatherRepository
import com.example.wheatherapptest.utils.base.BaseRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val apiService: WeatherApiService)
    :WeatherRepository, BaseRepository() {

    override suspend fun getWeather(country: String) = doRequest {
        apiService.getWeather(country).toWeather()
    }


}