package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.tracker_presentation.components.UnitDisplay

@Composable
fun NutrientInfo(
    text: String,
    value: Int,
    unit: String,
    valueSize: TextUnit = 14.sp,
    unitSize: TextUnit = 10.sp,
    color: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.displaySmall
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        UnitDisplay(
            value = value,
            unit = unit,
            valueSize = valueSize,
            unitSize = unitSize,
            color = color
        )
        Text(text = text, style = textStyle)
    }

}