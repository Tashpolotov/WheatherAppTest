package com.example.wheatherapptest.data.model.weather

data class SysData(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
