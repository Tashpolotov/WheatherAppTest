package com.example.wheatherapptest.domain.model.weather

data class WeatherModel(
    val coord: CoordModel?,
    val weather: List<Weather>?,
    val base: String,
    val main: MainModel,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)