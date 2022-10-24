package com.okproject.weatherapplication

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.okproject.weatherapplication.screen.ForecastScreen
import com.okproject.weatherapplication.screen.ForecastViewModel
import com.okproject.weatherapplication.screen.LocationViewModel
import com.okproject.weatherapplication.ui.theme.WeatherApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val locationViewModel: LocationViewModel by viewModels()
    private val forecastViewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ForecastScreen()
                }
            }
        }

        checkLocationPermission()
    }


    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            requestLastLocation()
        }
    }

    private fun checkLocationPermission() {
        when (
            ContextCompat.checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            )
        ) {
             PERMISSION_GRANTED -> {
                 requestLastLocation()
            }
            else -> {
                locationPermissionLauncher.launch(ACCESS_COARSE_LOCATION)
            }
        }
    }

    private fun requestLastLocation() {
        locationViewModel.requestLastLocation { latitude, longitude ->
            forecastViewModel.loadForecast(latitude, longitude)
        }
    }
}