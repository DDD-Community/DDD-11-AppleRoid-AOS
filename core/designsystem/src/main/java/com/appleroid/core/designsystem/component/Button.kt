package com.appleroid.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.theme.BTN_BACKGROUND
import com.appleroid.core.designsystem.theme.GREY01
import com.appleroid.core.designsystem.theme.POINT01

@Composable
fun MKungBtn(
    modifier: Modifier = Modifier,
    text : String = "",
    enable : Boolean,
    onClick : () -> Unit
){
    TextButton(
        modifier = modifier
            .background(
                color = BTN_BACKGROUND,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = if(enable) POINT01 else BTN_BACKGROUND,
                shape = RoundedCornerShape(12.dp)
            ),
        enabled = enable,
        onClick = onClick,
        content = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                color = if(enable) POINT01 else GREY01
            )
        }
    )
}