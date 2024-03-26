package com.example.wheatherapptest.data.remote

import com.example.wheatherapptest.data.model.weather.WeatherModelData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "21d3f3e7ff77c92921a02372c36c24f3"
    ): WeatherModelData

}