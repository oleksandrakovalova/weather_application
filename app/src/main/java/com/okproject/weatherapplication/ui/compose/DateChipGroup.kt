package com.okproject.weatherapplication.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DateChipGroup(
    dates: List<String>,
    isSelectedDate: (String) -> Boolean,
    onDateClicked: (String) -> Unit
) = LazyRow(
    horizontalArrangement = Arrangement.spacedBy(16.dp),
){
    items(dates) { date ->
        Chip(
            border = ChipDefaults.outlinedBorder,
            colors = ChipDefaults.outlinedChipColors(
                backgroundColor = chipColor(
                    isSelected = isSelectedDate(date)
                )
            ),
            onClick = { onDateClicked(date) }
        ) {
            Text(text = date, style = MaterialTheme.typography.body2)
        }
    }
}



@Composable
private fun chipColor(isSelected: Boolean) =
    if (isSelected) MaterialTheme.colors.primary.copy(0.25f)
    else MaterialTheme.colors.surface