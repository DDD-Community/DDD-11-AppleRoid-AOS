package com.appleroid.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CircleImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    imageRes: Int) {
    Image(
        painter = painterResource(imageRes),
        contentScale = contentScale,
        contentDescription = contentDescription,
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape)
    )
}