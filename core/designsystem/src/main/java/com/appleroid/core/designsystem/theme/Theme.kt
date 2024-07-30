package com.appleroid.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = GREY04,
    onPrimary = GREY02,
    secondary = GREY06,
    onSecondary = GREY04,
    tertiary = POINT01,
    background = BLACK
)

private val LightColorScheme = lightColorScheme(
    primary = GREY04,
    onPrimary = GREY02,
    secondary = GREY06,
    onSecondary = GREY04,
    tertiary = POINT01,
    background = BLACK

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MKungTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = mKungTypography(),
        content = content
    )
}