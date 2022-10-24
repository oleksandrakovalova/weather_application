package com.okproject.weatherapplication.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.okproject.weatherapplication.R
import com.okproject.weatherapplication.data.DailyDetails
import com.okproject.weatherapplication.data.ForecastUnit
import com.okproject.weatherapplication.screen.getSections
import com.okproject.weatherapplication.screen.getTemperatures
import com.okproject.weatherapplication.screen.getWeatherIcon

@Composable
fun ForecastCard(
    dailyDetails: DailyDetails,
    unit: ForecastUnit
) = Card(
    shape = RoundedCornerShape(8.dp),
    elevation = 3.dp,
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = rememberVectorPainter(
                image = ImageVector
                    .vectorResource(id = dailyDetails.getWeatherIcon())
            ),
            modifier = Modifier.size(160.dp),
            contentDescription = null
        )
        Text(dailyDetails.getTemperatures(unit),
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            dailyDetails.getSections(unit).forEach {
                InfoIconBox(
                    iconRes = it.iconRes,
                    title = it.title,
                    info = it.info
                )
            }
        }
    }
}