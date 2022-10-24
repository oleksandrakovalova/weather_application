package com.okproject.weatherapplication.network.response

import com.google.gson.annotations.SerializedName


data class ForecastResponse (
  @SerializedName("latitude")
  val latitude: Double?,

  @SerializedName("longitude")
  val longitude: Double?,

  @SerializedName("generationtime_ms")
  val generationTimeMs: Float?,

  @SerializedName("utc_offset_seconds")
  val utcOffsetSeconds: Long?,

  @SerializedName("timezone")
  val timezone: String?,

  @SerializedName("timezone_abbreviation")
  val timezoneAbbreviation : String?,

  @SerializedName("elevation")
  val elevation: Int?,

  @SerializedName("daily_units")
  val dailyUnits: UnitResponse?,

  @SerializedName("daily")
  val daily: DailyResponse?
)