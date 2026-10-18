package com.example.car_ui.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = CarAccentCyan,
    onPrimary = Color.Black,
    primaryContainer = CarSurfaceVariant,
    onPrimaryContainer = CarTextPrimary,
    secondary = CarAccentBlue,
    onSecondary = Color.White,
    secondaryContainer = CarCardBackground,
    onSecondaryContainer = CarTextPrimary,
    tertiary = CarAccentAmber,
    background = CarBackground,
    onBackground = CarTextPrimary,
    surface = CarSurface,
    onSurface = CarTextPrimary,
    surfaceVariant = CarSurfaceVariant,
    onSurfaceVariant = CarTextSecondary,
    outline = CarBorder
)

@Composable
fun Car_UITheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
