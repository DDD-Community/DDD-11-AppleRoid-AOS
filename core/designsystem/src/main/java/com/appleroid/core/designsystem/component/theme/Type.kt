package com.appleroid.core.designsystem.component.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appleroid.core.designsystem.component.utils.textSp

@Composable
fun mKungTypography() = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.dp.textSp,
        lineHeight = 24.dp.textSp,
        letterSpacing = 0.dp.textSp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.dp.textSp,
        lineHeight = 28.dp.textSp,
        letterSpacing = 0.dp.textSp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.dp.textSp,
        lineHeight = 20.dp.textSp,
        letterSpacing = 0.dp.textSp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.dp.textSp,
        lineHeight = 20.dp.textSp,
        letterSpacing = 0.dp.textSp,
        color = WHITE
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.dp.textSp,
        lineHeight = 16.dp.textSp,
        letterSpacing = 0.dp.textSp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.dp.textSp,
        lineHeight = 16.dp.textSp,
        letterSpacing = 0.dp.textSp
    ),
)