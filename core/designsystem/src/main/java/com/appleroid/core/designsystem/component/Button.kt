package com.appleroid.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.theme.BLACK
import com.appleroid.core.designsystem.theme.DOT
import com.appleroid.core.designsystem.theme.GREY05
import com.appleroid.core.designsystem.theme.GREY08
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
fun ImageWithTextBtn(
    modifier: Modifier = Modifier,
    selectedImageRes: Int,
    unselectedImageRes: Int,
    text: String,
    interval: Dp = 2.dp,
    arrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    orientation: Orientation = Orientation.Horizontal,
    onClick: (Boolean) -> Unit,
) {
    var isClicked by remember { mutableStateOf(false) }
    val imageResource = if (isClicked) selectedImageRes else unselectedImageRes

    val content: @Composable () -> Unit = {
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.padding(
                end = if (orientation == Orientation.Horizontal) interval else 0.dp,
                bottom = if (orientation == Orientation.Vertical) interval else 0.dp
            )
        )
        LabelText(
            modifier = modifier,
            text = text,
            color = WHITE,
            style = MaterialTheme.typography.labelSmall
        )
    }

    when (orientation) {
        Orientation.Horizontal -> Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    isClicked = !isClicked
                    onClick(isClicked)
                },
            horizontalArrangement = arrangement,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }

        Orientation.Vertical -> Column(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    isClicked = !isClicked
                    onClick(isClicked)
                },
            verticalArrangement = verticalArrangement,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Composable
fun LabelBtn(
    modifier: Modifier,
    text: String,
    textColor: Color,
    borderColor : Color,
    horizontalPadding : Dp
){
    Box(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(17.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(17.dp)
            )
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
                .padding(top = 2.dp, bottom = 3.5.dp)
        )
    }
}
