package com.okproject.weatherapplication.screen

import androidx.annotation.DrawableRes
import com.okproject.weatherapplication.R
import com.okproject.weatherapplication.data.DailyDetails
import com.okproject.weatherapplication.data.ForecastUnit

fun DailyDetails.getTemperatures(unit: ForecastUnit): String {
    return "$minTemperature\u2026$maxTemperature${unit.temperature}"
}

fun DailyDetails.getSections(unit: ForecastUnit): List<DailyWeatherSection> = listOf(
    DailyWeatherSection(
        R.drawable.ic_sunrise,
        "Sunrise",
        sunrise
    ),
    DailyWeatherSection(
        R.drawable.ic_sunset,
        "Sunset",
        sunset
    ),
    DailyWeatherSection(
        R.drawable.ic_wind,
        "Wind",
        "%.2f ${unit.windSpeedMax}".format(maxWindSpeed)
    )
)

@DrawableRes
fun DailyDetails.getWeatherIcon(): Int = when(weathercode) {
    0 -> R.drawable.sunny
    in 1..40 -> R.drawable.partly_cloudy
    in 41..50 -> R.drawable.fog
    in 51..60 -> R.drawable.drizzle
    in 61..70 -> R.drawable.rain
    in 71..79 -> R.drawable.snow
    in 80..84 -> R.drawable.heavy_rain
    in 85..94 -> R.drawable.snowfall
    else -> R.drawable.thunderstorm
}