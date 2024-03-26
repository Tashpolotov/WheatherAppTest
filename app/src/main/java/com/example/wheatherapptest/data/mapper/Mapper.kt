package com.example.wheatherapptest.data.mapper

import com.example.wheatherapptest.data.model.weather.CloudsData
import com.example.wheatherapptest.data.model.weather.CoordModelData
import com.example.wheatherapptest.data.model.weather.MainModelData
import com.example.wheatherapptest.data.model.weather.SysData
import com.example.wheatherapptest.data.model.weather.WeatherData
import com.example.wheatherapptest.data.model.weather.WeatherModelData
import com.example.wheatherapptest.data.model.weather.WindData
import com.example.wheatherapptest.domain.model.weather.Clouds
import com.example.wheatherapptest.domain.model.weather.CoordModel
import com.example.wheatherapptest.domain.model.weather.MainModel
import com.example.wheatherapptest.domain.model.weather.Sys
import com.example.wheatherapptest.domain.model.weather.Weather
import com.example.wheatherapptest.domain.model.weather.WeatherModel
import com.example.wheatherapptest.domain.model.weather.Wind

fun WeatherModelData.toWeather() = WeatherModel(
    coord?.toCoord(), weather?.toWeatherList(), base, main.toMain(), visibility, wind.toWind(),
    clouds.toCloud(), dt, sys.toSys(), timezone, id, name, cod
)

fun List<WeatherData>.toWeatherList(): List<Weather> {
    return map { it.toWeather() }
}

fun WeatherData.toWeather() = Weather(
    id, main, description, icon
)

fun CoordModelData.toCoord() = CoordModel(
    lon, lat
)

fun SysData.toSys() = Sys(
    type, id, country, sunrise, sunset
)

fun CloudsData.toCloud() = Clouds(
    all
)

fun MainModelData.toMain() = MainModel(
    temp, feelsLike, tempMin, tempMax, pressure, humidity, seaLevel, grndLevel
)

fun WindData.toWind() = Wind(
    speed, deg, gust
)