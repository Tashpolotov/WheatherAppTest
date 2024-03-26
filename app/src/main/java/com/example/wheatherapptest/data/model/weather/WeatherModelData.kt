package com.example.wheatherapptest.data.model.weather

data class WeatherModelData(
    val coord: CoordModelData?,
    val weather: List<WeatherData>?,
    val base: String,
    val main: MainModelData,
    val visibility: Int,
    val wind: WindData,
    val clouds: CloudsData,
    val dt: Long,
    val sys: SysData,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)