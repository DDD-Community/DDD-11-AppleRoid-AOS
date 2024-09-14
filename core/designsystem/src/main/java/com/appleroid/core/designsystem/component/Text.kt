package com.appleroid.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.appleroid.core.designsystem.theme.GREY01
import com.appleroid.core.designsystem.theme.WHITE

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    title : String = "",
    textAlign: TextAlign = TextAlign.Left,
    alignment: Alignment = Alignment.CenterStart
){
    Box(
        modifier = modifier
    ){
        Text(
            text = title,
            color = WHITE,
            style = MaterialTheme.typography.labelLarge,
            textAlign = textAlign,
            modifier = Modifier.align(alignment)
        )
    }
}

@Composable
fun DescriptionText(
    modifier: Modifier = Modifier,
    title : String = "",
    alignment: Alignment = Alignment.CenterStart,
    textAlign: TextAlign = TextAlign.Left
){
    Box(
        modifier = modifier
    ){
        Text(
            text = title,
            color = GREY01,
            style = MaterialTheme.typography.labelMedium,
            textAlign = textAlign,
            modifier = Modifier.align(alignment)
        )
    }
}


@Composable
fun LabelText(
    modifier: Modifier = Modifier,
    text : String = "",
    alignment: Alignment = Alignment.CenterStart,
    style : TextStyle,
    color : Color
){
    Box(
        modifier = modifier
    ){
        Text(
            text = text,
            color = color,
            style = style,
            modifier = Modifier
                .align(alignment),
        )
    }
}