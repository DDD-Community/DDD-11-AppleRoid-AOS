package com.appleroid.feature.phoneverify

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PhoneVerifyRoute() {
    PhoneVerifyScreen()
}


@Composable
fun PhoneVerifyScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(bottom = 100.dp)
    ) {
        Column {
            TopLayer()
            NotificationTitles()
            InfoInputCard()

            Spacer(modifier = Modifier.weight(1f))

            PhoneVerifyButton(onClick = {

            })
        }
    }
}

@Composable
fun TopLayer() {
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
            text = stringResource(R.string.title),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun NotificationTitles() {
    Text(
        modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp),
        text = stringResource(R.string.welcome_message),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Start
        )
    )

    Text(
        modifier = Modifier.padding(start = 24.dp, top = 10.dp, end = 24.dp),
        text = stringResource(R.string.verification_message),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = colorResource(R.color.color_grey04),
            textAlign = TextAlign.Start
        )
    )
}

@Composable
fun InfoInputCard() {
    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 30.dp)
            .wrapContentSize()
            .background(
                color = colorResource(R.color.card_background),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column {
            InputPhoneNumber()
            InputName()
        }
    }
}

@Composable
fun InputPhoneNumber() {
    var text by remember { mutableStateOf("") }
    val regex = "^01[0-1|6-9]\\d{3,4}\\d{4}$".toRegex()

    Box(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 18.dp)
    ) {
        Column {
            Text(
                modifier = Modifier.height(18.dp),
                text = stringResource(R.string.card_phone_number_title),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(R.color.color_grey01),
                    textAlign = TextAlign.Start
                )
            )

            Row {
                Text(
                    modifier = Modifier.height(27.dp),
                    text = stringResource(R.string.card_telecom),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                )

                Image(
                    modifier = Modifier
                        .height(10.dp)
                        .width(13.dp)
                        .align(Alignment.CenterVertically)
                        .padding(start = 2.dp),
                    painter = painterResource(R.drawable.ic_polygon),
                    contentDescription = "통신사 선택 아이콘")

                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(27.dp)
                        .padding(start = 10.dp)
                        .background(Color.Transparent)
                        .align(Alignment.CenterVertically),
                    value = text,
                    cursorBrush = SolidColor(Color.Transparent),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    onValueChange = {
                        text = it
                    },
                    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                    singleLine = true
                )
            }

            Box(modifier = Modifier
                .padding(top = 13.dp)
                .height(1.dp)
                .fillMaxWidth()
                .background(colorResource(R.color.color_grey04))
            )
        }
    }
}

@Composable
fun InputName() {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 11.dp, bottom = 18.dp)
    ) {
        Column {
            Text(
                modifier = Modifier.height(18.dp),
                text = stringResource(R.string.card_name_title),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(R.color.color_grey01),
                    textAlign = TextAlign.Start
                )
            )

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(27.dp)
                    .background(Color.Transparent),
                value = text,
                cursorBrush = SolidColor(Color.Transparent),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                ),
                onValueChange = {
                    text = it
                },
                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                singleLine = true
            )
        }
    }
}

@Composable
fun PhoneVerifyButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, colorResource(R.color.button_border)),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.card_background),
            contentColor = colorResource(R.color.card_background)
        )
    ) {
        Text(
            modifier = Modifier.height(24.dp),
            text = stringResource(R.string.verification),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.button_border),
                textAlign = TextAlign.Center
            )
        )
    }
}