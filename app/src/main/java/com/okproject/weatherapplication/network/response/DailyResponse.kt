package com.okproject.weatherapplication.network.response

import com.google.gson.annotations.SerializedName


data class DailyResponse (
  @SerializedName("time")
  val time: List<String>?,

  @SerializedName("weathercode")
  val weathercode: List<Int>?,

  @SerializedName("temperature_2m_max")
  val temperature2mMax: List<Float>?,

  @SerializedName("temperature_2m_min")
  val temperature2mMin: List<Float>?,

  @SerializedName("sunrise")
  val sunrise: List<String>?,

  @SerializedName("sunset")
  val sunset: List<String>?,

  @SerializedName("windspeed_10m_max")
  val windSpeed10mMax: List<Float>?
)