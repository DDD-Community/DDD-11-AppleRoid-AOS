package com.appleroid.feature.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignUpRoute() {
    SignUpScreen()
}


@Composable
fun SignUpScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            TopLayerScreen()
            TitleScreen()
        }
    }
}

@Composable
fun TopLayerScreen() {
    Box(
        modifier = Modifier
            .height(79.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 50.dp),
            text = stringResource(R.string.sign_up_phone_verification),
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 19.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun TitleScreen() {
    Text(
        modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp),
        text = stringResource(R.string.sign_up_title_welcome_message),
        style = TextStyle(
            fontSize = 18.sp,
            lineHeight = 27.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Start
        )
    )

    Text(
        modifier = Modifier.padding(start = 24.dp, top = 10.dp, end = 24.dp),
        text = stringResource(R.string.sign_up_phone_verification_message),
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 21.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            textAlign = TextAlign.Start
        )
    )
}