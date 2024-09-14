package com.appleroid.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.R

@Preview
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title : String = ""
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ){
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
fun BackWithTextTopAppBar(
    modifier: Modifier = Modifier,
    height: Dp,
    title : String = ""
) {
    Box (modifier = Modifier
        .height(height)
        .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(20.dp))

        Image(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = "back button",
            modifier = modifier
                .align(Alignment.CenterStart)
                .size(28.dp)
                .clickable {
                }
        )

        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}