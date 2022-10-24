package com.okproject.weatherapplication.repository

import com.okproject.weatherapplication.data.DailyDetails
import com.okproject.weatherapplication.data.ForecastUnit

interface ForecastRepository {
    suspend fun loadForecast(latitude: Double, longitude: Double)
    fun getDates(): List<String>
    fun getDailyForecast(date: String): DailyDetails?
    fun getForecastUnit(): ForecastUnit?
}