package com.example.onboarding_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing


@Composable
fun SelectableButton(
    modifier: Modifier = Modifier,
    color: Color,
    selectedTextColor: Color,
    borderWidth: Dp = 2.dp,
    isSelected: Boolean,
    cornerClip: Dp = 100.dp,
    text: String,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(cornerClip))
            .border(
                color =color,
                width = borderWidth,
                shape = RoundedCornerShape(cornerClip)
            )
            .background(
                color = if (isSelected) color else Color.Transparent,
                shape = RoundedCornerShape(cornerClip)
            )
            .clickable {
                onClick()
            }
            .padding(vertical = spacing.spaceSmall , horizontal = spacing.spaceMedium)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = if (isSelected) selectedTextColor else color
        )
    }
}
