package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LozAiDarkColorScheme = darkColorScheme(
    primary = LozAiPurplePrimary,
    onPrimary = Color.White,
    primaryContainer = LozAiPurpleDark,
    onPrimaryContainer = LozAiPurpleLight,
    secondary = LozAiCyan,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF0E7490),
    onSecondaryContainer = Color(0xFFA5F3FC),
    tertiary = LozAiSky,
    onTertiary = Color.Black,
    background = LozAiBg,
    onBackground = LozAiTextPrimary,
    surface = LozAiSurface,
    onSurface = LozAiTextPrimary,
    surfaceVariant = LozAiSurfaceElevated,
    onSurfaceVariant = LozAiTextSecondary,
    outline = LozAiCardBorder
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Always dark theme for LozAI
    dynamicColor: Boolean = false, // Keep exact LozAI branding
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LozAiDarkColorScheme,
        typography = Typography,
        content = content
    )
}
