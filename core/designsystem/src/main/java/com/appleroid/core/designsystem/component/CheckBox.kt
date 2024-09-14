package com.appleroid.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.R
import com.appleroid.core.designsystem.theme.BTN_BACKGROUND
import com.appleroid.core.designsystem.theme.DOT
import com.appleroid.core.designsystem.theme.GREY01
import com.appleroid.core.designsystem.theme.GREY02
import com.appleroid.core.designsystem.theme.GREY04
import com.appleroid.core.designsystem.theme.GREY05
import com.appleroid.core.designsystem.theme.POINT01
import com.appleroid.core.designsystem.theme.POINT01_23
import com.appleroid.core.designsystem.theme.WHITE
import com.appleroid.core.designsystem.utils.textSp

@Composable
fun WithTextCheckBoxCard(
    modifier: Modifier = Modifier,
    isSelected : Boolean = false,
    text : String = "",
    percentText: String = "",
    onSelected : (Boolean) -> Unit
){
    Box(
        modifier = modifier
            .background(
                color = GREY05,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = if(isSelected) POINT01 else GREY05,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onSelected(!isSelected) }
    ){
        Row(
            modifier = Modifier.align(Alignment.CenterStart)
        ){
            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(if(isSelected) R.drawable.ic_select_check else R.drawable.ic_unselect_check),
                contentDescription = "icon_check",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterVertically)
            )

            LabelText(
                text = text,
                color = if(isSelected) POINT01 else WHITE,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            )

            if (percentText.isNotBlank()) {
                Spacer(modifier = Modifier.weight(1F))

                Text(
                    text = percentText,
                    color = WHITE,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun MbtiCheckBox(
    modifier: Modifier = Modifier,
    key : String,
    text : String,
    isChecked : Boolean,
    onClick : (String) -> Unit
){
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .clickable { onClick(key) }
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 2.dp,
                color = if(isChecked) POINT01 else GREY04,
                shape = RoundedCornerShape(12.dp)
            )
            .background(color = GREY04)
        ){
            LabelText(
                modifier = Modifier
                    .height(44.dp)
                    .align(Alignment.Center),
                text = text,
                color = if(isChecked) POINT01 else GREY02,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.dp.textSp,
                    lineHeight = 21.dp.textSp,
                    letterSpacing = 0.dp.textSp,
                    textAlign = TextAlign.Center
                ),
                alignment = Alignment.Center
            )
        }
    }
}

@Composable
fun WithTextCheckBox(
    modifier: Modifier = Modifier,
    isSelected : Boolean = false,
    text : String = "",
    onSelected : (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onSelected(!isSelected) }
    ){
        Row(
            modifier = Modifier.align(Alignment.CenterStart)
        ){
            Image(
                painter = painterResource(if(isSelected) R.drawable.ic_select_check else R.drawable.ic_unselect_check),
                contentDescription = "icon_check",
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = text,
                color = if(isSelected) WHITE else GREY01,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}