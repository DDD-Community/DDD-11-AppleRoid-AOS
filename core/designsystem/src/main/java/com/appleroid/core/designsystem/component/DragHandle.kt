package com.appleroid.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.theme.GREY05

@Composable
fun CustomDragHandle(
    width: Dp = 56.dp,
    height: Dp = 5.dp,
    color: Color = GREY05,
    cornerRadius: Dp = 2.dp,
    paddingTop: Dp = 12.dp
) {
    Box(
        modifier = Modifier.padding(top = paddingTop)
    ) {
        Box(
            modifier = Modifier
                .width(width)
                .height(height)
                .clip(RoundedCornerShape(cornerRadius))
                .background(color)
        )
    }
}