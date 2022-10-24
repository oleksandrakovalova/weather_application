package com.okproject.weatherapplication.repository

import com.okproject.weatherapplication.data.DailyDetails
import com.okproject.weatherapplication.data.ForecastUnit
import com.okproject.weatherapplication.network.response.DailyResponse
import com.okproject.weatherapplication.network.response.ForecastResponse
import kotlin.math.roundToInt

object ForecastMapper {
    fun mapResponseToDailyForecast(response: ForecastResponse): Map<String, DailyDetails> {
        val forecastMap = mutableMapOf<String, DailyDetails>()
        response.daily?.let { daily ->
            daily.time?.forEachIndexed { index, date ->
                val dailyDetails = daily.getDetails(index)
                forecastMap[date] = dailyDetails
            }
        }
        return forecastMap
    }

    fun mapResponseToUnits(response: ForecastResponse): ForecastUnit {
        val units = response.dailyUnits
        return ForecastUnit(
            units?.temperature2mMin ?: units?.temperature2mMax ?: "\u2103",
            units?.windSpeed10mMax ?: "km/h"
        )
    }

    private fun DailyResponse.getDetails(index: Int): DailyDetails = DailyDetails(
        weathercode?.getOrNull(index) ?: -1,
        temperature2mMin.getRoundedValue(index),
        temperature2mMax.getRoundedValue(index),
        sunrise.getTimeValue(index),
        sunset.getTimeValue(index),
        windSpeed10mMax?.getOrNull(index) ?: -1f
    )

    private fun List<Float>?.getRoundedValue(index: Int): Int = this?.getOrNull(index)?.roundToInt() ?: -100
    private fun List<String>?.getTimeValue(index: Int): String = this?.getOrNull(index)?.substringAfter('T') ?: ""
}