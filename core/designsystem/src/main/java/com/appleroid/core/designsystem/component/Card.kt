package com.appleroid.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.theme.BLACK
import com.appleroid.core.designsystem.theme.WHITE

@Composable
fun MbtiCard(modifier: Modifier = Modifier,
             shape: Shape = RoundedCornerShape(20.dp),
             text: String,
             textColor: Color = WHITE,
             cardColor: Color = BLACK) {
    Card(
        modifier = modifier.width(48.dp).height(18.dp),
        shape = shape,
        colors = CardColors(
            contentColor = cardColor,
            containerColor = cardColor,
            disabledContentColor = cardColor,
            disabledContainerColor = cardColor
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}