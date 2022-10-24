package com.okproject.weatherapplication.repository

import com.okproject.weatherapplication.data.DailyDetails
import com.okproject.weatherapplication.data.ForecastUnit
import com.okproject.weatherapplication.network.*
import com.okproject.weatherapplication.network.response.ForecastResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val api: OpenMeteoForecastApi
): ForecastRepository {
    private val forecast: MutableMap<String, DailyDetails> = mutableMapOf()
    private var unit: ForecastUnit? = null

    override suspend fun loadForecast(
        latitude: Double, longitude: Double
    ) = withContext(Dispatchers.IO) {
        val response = getForecastFromNetwork(latitude, longitude)
        updateUnits(response)
        updateForecast(response)
    }

    override fun getDates(): List<String> = forecast.keys.toList()

    override fun getDailyForecast(date: String): DailyDetails? = forecast[date]

    override fun getForecastUnit(): ForecastUnit? = unit

    private suspend fun getForecastFromNetwork(
        latitude: Double, longitude: Double
    ): ForecastResponse = withContext(Dispatchers.IO) {
        val response = api.getForecast(
            forecastRequestQuery(latitude, longitude),
            dailyQuery
        )
        if (response.isSuccessful) {
            response.body() ?: throw EmptyResponseBodyException(response.raw().toString())
        } else throw ApiResponseException(response.errorBody()?.string().toString())
    }

    private fun updateForecast(response: ForecastResponse) {
        forecast.clear()
        val updatedForecast =
            ForecastMapper.mapResponseToDailyForecast(response)
        forecast.putAll(updatedForecast)
    }

    private fun updateUnits(response: ForecastResponse) {
        unit = ForecastMapper.mapResponseToUnits(response)
    }
}