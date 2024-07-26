package com.appleroid.feature.mbti

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MbtiRoute(){
    MbtiScreen()
}


@Composable
fun MbtiScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .systemBarsPadding()
            .padding(bottom = 47.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopLayer()
            NotificationTitles()
            /*InfoInputCard()

            Spacer(modifier = Modifier.weight(1f))

            PhoneVerifyButton()*/
        }
    }
}

@Composable
fun TopLayer() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp),
        text = stringResource(R.string.mbti_setting_title),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
fun NotificationTitles() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            text = stringResource(R.string.mbti_setting_challenge_message),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "가나다라마바사",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.color_grey04),
                textAlign = TextAlign.Center
            )
        )

        Image(
            modifier = Modifier,
            painter = painterResource(R.drawable.ic_mbti_setting),
            contentDescription = null
        )
    }
}