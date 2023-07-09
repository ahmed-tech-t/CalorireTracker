package com.example.calorietracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

import com.example.core_ui.BrightOrange
import com.example.core_ui.DarkGray
import com.example.core_ui.DarkOrange
import com.example.core_ui.Dimentions
import com.example.core_ui.Green
import com.example.core_ui.LightGray
import com.example.core_ui.LocalSpacing
import com.example.core_ui.MediumGray
import com.example.core_ui.TextWhite

private val DarkColorScheme = darkColorScheme(
    primary = BrightOrange,
    primaryContainer = DarkOrange,
    secondary = Green,
    background = MediumGray,
    onBackground = TextWhite,
    surface = LightGray,
    onSurface = TextWhite,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = BrightOrange,
    primaryContainer = DarkOrange,
    secondary = Green,
    background = Color.White,
    onBackground = DarkGray,
    surface = Color.White,
    onSurface = DarkGray,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

@Composable
fun CalorieTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    CompositionLocalProvider(LocalSpacing provides Dimentions()) {
        MaterialTheme(
            colorScheme = colors,
            typography = Typography,
            content = content
        )
    }
}