package com.okproject.weatherapplication.screen

import android.annotation.SuppressLint
import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.okproject.weatherapplication.data.LocationDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(application: Application): AndroidViewModel(application) {
    val locationDetailsState: MutableLiveData<LocationDetails?> = MutableLiveData(null)

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)
    private val geocoder = Geocoder(application)

    @SuppressLint("MissingPermission")
    fun requestLastLocation(
        onSuccessListener: (longitude: Double, latitude: Double) -> Unit
    ) {
        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    updateForecastLocation(location)
                    onSuccessListener.invoke(
                        location.latitude,
                        location.longitude
                    )
                }
        } catch (exception: Exception) {
            Log.e(TAG, "requestLastLocation() is failed with exception:", exception)
        }
    }

    private fun updateForecastLocation(location: Location?) {
        if (location != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                listenToAddressFromLocation(location)
            } else {
                val address = getAddressFromLocation(location)
                setForecastLocation(address)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun getAddressFromLocation(location: Location): Address? =
        geocoder.getFromLocation(location.latitude, location.longitude, 1)?.firstOrNull()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun listenToAddressFromLocation(location: Location) {
        geocoder.getFromLocation(location.latitude, location.longitude, 1) { addresses ->
            setForecastLocation(addresses.firstOrNull())
        }
    }

    private fun setForecastLocation(address: Address?) {
        if (address != null) {
            locationDetailsState.value = LocationDetails(
                address.locality,
                address.countryName,
                address.latitude,
                address.longitude
            )
        }
    }

    companion object {
        private val TAG = LocationViewModel::class.simpleName
    }
}