package com.okproject.weatherapplication.data

data class LocationDetails(
    val city: String?,
    val country: String?,
    val latitude: Double,
    val longitude: Double
) {
    fun getLocality(): String = "$city, $country"
}
