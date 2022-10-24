package com.okproject.weatherapplication.data

data class DailyDetails(
    val weathercode: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val sunrise: String,
    val sunset: String,
    val maxWindSpeed: Float
)