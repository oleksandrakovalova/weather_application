package com.okproject.weatherapplication.network.response

import com.google.gson.annotations.SerializedName


data class UnitResponse (
  @SerializedName("time")
  val time: String?,

  @SerializedName("weathercode")
  val weathercode: String?,

  @SerializedName("temperature_2m_max")
  val temperature2mMax: String?,

  @SerializedName("temperature_2m_min")
  val temperature2mMin: String?,

  @SerializedName("sunrise")
  val sunrise: String?,

  @SerializedName("sunset")
  val sunset: String?,

  @SerializedName("windspeed_10m_max")
  val windSpeed10mMax: String?
)