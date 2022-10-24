package com.okproject.weatherapplication.network

fun forecastRequestQuery(latitude: Double, longitude: Double) = mapOf(
    "latitude" to latitude.toString(),
    "longitude" to longitude.toString(),
    "timezone" to "auto"
)

val dailyQuery = listOf(
    "weathercode",
    "temperature_2m_max",
    "temperature_2m_min",
    "sunrise",
    "sunset",
    "windspeed_10m_max"
)