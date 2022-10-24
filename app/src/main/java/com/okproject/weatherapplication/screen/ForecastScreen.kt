package com.okproject.weatherapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.okproject.weatherapplication.data.DailyDetails
import com.okproject.weatherapplication.data.ForecastUnit
import com.okproject.weatherapplication.ui.compose.DateChipGroup
import com.okproject.weatherapplication.ui.compose.DefaultSnackbar
import com.okproject.weatherapplication.ui.compose.ForecastCard
import com.okproject.weatherapplication.ui.compose.LocationLabel
import com.okproject.weatherapplication.ui.state.Loader

@Composable
fun ForecastScreen() {
    val locationViewModel: LocationViewModel = viewModel()
    val forecastViewModel: ForecastViewModel = viewModel()
    val scaffoldState = rememberScaffoldState()

    forecastViewModel.errorState.value?.let { error ->
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = error
            )
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            snackbarHost = { scaffoldState.snackbarHostState }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    HorizontalProgress(loader = forecastViewModel.loadingState.value)
                    locationViewModel.locationDetailsState.value?.let {
                        LocationLabel(
                            location = it,
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.End)
                        )
                    }
                    DateChipGroup(
                        dates = forecastViewModel.daysState.value,
                        isSelectedDate = { date -> forecastViewModel.isSelectedDate(date) },
                        onDateClicked = { date -> forecastViewModel.selectDailyForecast(date) }
                    )
                    DailyForecastCard(
                        details = forecastViewModel.dailyForecastState.value,
                        unit = forecastViewModel.unitState.value
                    )
                }
                DefaultSnackbar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    snackbarHostState = scaffoldState.snackbarHostState,
                    onDismiss = {
                        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                    }
                )
            }
        }
    }
}

@Composable
private fun DailyForecastCard(
    details: DailyDetails?,
    unit: ForecastUnit?
) {
    if (details != null && unit != null)
        ForecastCard(dailyDetails = details, unit = unit)
}

@Composable
private fun HorizontalProgress(loader: Loader) {
    if (loader == Loader.LOADING) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
    }
}