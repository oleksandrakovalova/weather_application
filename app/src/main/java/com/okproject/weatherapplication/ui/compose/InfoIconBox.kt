package com.okproject.weatherapplication.ui.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun InfoIconBox(
    @DrawableRes iconRes: Int,
    title: String,
    info: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = rememberVectorPainter(
                image = ImageVector.vectorResource(id = iconRes)
            ),
            modifier = Modifier.size(36.dp)
                .padding(8.dp),
            contentDescription = null
        )
        Text(title, style = MaterialTheme.typography.subtitle1)
        Text(info,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(8.dp)
        )
    }
}