package com.example.tracker_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing

@Composable
fun UnitDisplay(
    value: Int,
    unit: String,
    valueSize: TextUnit = 20.sp,
    unitSize: TextUnit = 14.sp,
    color: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {

    val spacer = LocalSpacing.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = value.toString(),
            fontSize = valueSize,
            style = MaterialTheme.typography.displaySmall,
            color = color,
            modifier = Modifier.alignBy(LastBaseline)
        )
        Spacer(modifier = Modifier.width(spacer.spaceExtraSmall))
        Text(
            text = unit,
            fontSize = unitSize,
            style = MaterialTheme.typography.displaySmall,
            color = color,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}