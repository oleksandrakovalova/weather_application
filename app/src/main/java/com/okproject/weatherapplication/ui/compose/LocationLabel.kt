package com.okproject.weatherapplication.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.okproject.weatherapplication.data.LocationDetails


@Composable
fun LocationLabel(
    location: LocationDetails,
    modifier: Modifier
) = Card(
    modifier = modifier,
    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
    shape = RoundedCornerShape(4.dp),
    elevation = 3.dp
) {
    Row(modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = rememberVectorPainter(
                image = ImageVector.vectorResource(
                    id = R.drawable.ic_location
                )
            ),
            modifier = Modifier.size(16.dp),
            contentDescription = null
        )
        Text(
            location.getLocality(),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}