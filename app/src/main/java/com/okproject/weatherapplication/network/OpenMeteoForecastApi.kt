package com.okproject.weatherapplication.network

import com.okproject.weatherapplication.network.response.ForecastResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface OpenMeteoForecastApi {

    @GET("forecast")
    suspend fun getForecast(
        @QueryMap query: Map<String, String>,
        @Query("daily") daily: List<String>
    ): Response<ForecastResponse>

    companion object {
        val instance: OpenMeteoForecastApi by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(OpenMeteoForecastApi::class.java)
        }
        const val BASE_URL = "https://api.open-meteo.com/v1/"
    }
}