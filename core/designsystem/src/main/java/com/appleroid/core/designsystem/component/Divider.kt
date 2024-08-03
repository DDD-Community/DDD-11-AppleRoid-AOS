package com.appleroid.core.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp

@Composable
fun DottedDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    thickness: Float = 1f,
    interval: Float = 12f
) {
    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(thickness.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            color = color,
            start = Offset(0f, canvasHeight / 2),
            end = Offset(canvasWidth, canvasHeight / 2),
            strokeWidth = thickness,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(interval, interval), 0f)
        )
    }
}
