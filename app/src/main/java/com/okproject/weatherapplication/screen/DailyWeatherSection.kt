package com.okproject.weatherapplication.screen

import androidx.annotation.DrawableRes

data class DailyWeatherSection(
    @DrawableRes
    val iconRes:  Int,
    val title: String,
    val info: String
)
