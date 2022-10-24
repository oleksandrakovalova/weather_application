package com.okproject.weatherapplication.screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okproject.weatherapplication.data.DailyDetails
import com.okproject.weatherapplication.data.ForecastUnit
import com.okproject.weatherapplication.repository.ForecastRepository
import com.okproject.weatherapplication.ui.state.Loader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val forecastRepository: ForecastRepository
): ViewModel() {
    val loadingState: MutableState<Loader> = mutableStateOf(Loader.INIT)
    val daysState: MutableState<List<String>> = mutableStateOf(listOf())
    private val selectedDayState: MutableState<String?> = mutableStateOf(null)
    val dailyForecastState: MutableState<DailyDetails?> = mutableStateOf(null)
    val unitState: MutableState<ForecastUnit?> = mutableStateOf(null)
    val errorState: MutableState<String?> = mutableStateOf(null)

    fun loadForecast(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                loadingState.value = Loader.LOADING
                forecastRepository.loadForecast(latitude, longitude)
                daysState.value = forecastRepository.getDates()
                unitState.value = forecastRepository.getForecastUnit()
                daysState.value.firstOrNull()?.let {
                    selectDailyForecast(it)
                }
            } catch (exception: Exception) {
                errorState.value = exception.message
                Log.e(TAG, "Forecast request is failed with exception: ${exception.message}", exception)
            } finally {
                loadingState.value = Loader.LOADED
            }
        }
    }

    fun isSelectedDate(date: String): Boolean = date == selectedDayState.value

    fun selectDailyForecast(date: String) {
        selectedDayState.value = date
        dailyForecastState.value = forecastRepository.getDailyForecast(date)
    }

    companion object {
        private val TAG = ForecastViewModel::class.java.simpleName
    }
}