package com.example.wheatherapptest.domain.repository

import com.example.wheatherapptest.domain.model.weather.WeatherModel
import com.example.wheatherapptest.utils.resource.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeather(country:String):Flow<Resource<WeatherModel>>


}