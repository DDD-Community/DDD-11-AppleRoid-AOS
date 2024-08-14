package com.appleroid.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.theme.BLACK
import com.appleroid.core.designsystem.theme.BTN_BACKGROUND
import com.appleroid.core.designsystem.theme.DOT
import com.appleroid.core.designsystem.theme.GREY01
import com.appleroid.core.designsystem.theme.GREY05
import com.appleroid.core.designsystem.theme.POINT01
import com.appleroid.core.designsystem.theme.WHITE

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
                color = if(enable) POINT01 else GREY05,
                shape = RoundedCornerShape(12.dp)
            ),
        enabled = enable,
        onClick = onClick,
        content = {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                color = if(enable) BLACK else DOT
            )
        }
    )
}

@Composable
fun ImageWithTextBtn (
    modifier: Modifier = Modifier,
    selectedImageRes: Int,
    unselectedImageRes: Int,
    text: String,
    interval: Dp = 2.dp,
    arrangement: Arrangement.Horizontal,
    onClick: () -> Unit
) {
    var isClicked by remember { mutableStateOf(false) }
    val imageResource = if (isClicked) selectedImageRes else unselectedImageRes

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                isClicked = !isClicked
                onClick()
            },
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.padding(end = interval)
        )
        LabelText(
            modifier = modifier.align(Alignment.CenterVertically),
            text = text,
            color = WHITE,
            style = MaterialTheme.typography.labelSmall
        )
    }
}