package com.example.wheatherapptest.domain.model.weather

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)