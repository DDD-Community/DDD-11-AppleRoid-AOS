package com.appleroid.feature.nickname

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NickNameRoute(){
    NickNameScreen()
}


@Composable
fun NickNameScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .systemBarsPadding()
            .padding(bottom = 47.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopLayer()
            NotificationTitles()
            NickNameInputCard()

            Spacer(modifier = Modifier.weight(1f))

            ContinueButton()
        }
    }
}

@Composable
fun TopLayer() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp),
        text = stringResource(R.string.nick_name_title),
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
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {

        Text(
            modifier = Modifier
                .padding(top = 20.dp),
            text = stringResource(R.string.nick_name_welcome_message),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Start
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.nick_name_verification_message),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.color_grey04),
                textAlign = TextAlign.Start
            )
        )
    }
}

@Composable
fun NickNameInputCard() {
    var text by remember { mutableStateOf("") }

    Text(
        modifier = Modifier.padding(top = 24.dp, start = 24.dp, bottom = 5.dp),
        text = stringResource(R.string.nick_name),
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = colorResource(R.color.color_grey01),
            textAlign = TextAlign.Start
        )
    )

    Box(
        modifier = Modifier
            .height(42.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.card_background))
    ) {
        Row (modifier = Modifier
            .fillMaxSize()
            .padding(start = 14.dp, end = 14.dp)) {

            BasicTextField(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .background(Color.Transparent),
                value = text,
                cursorBrush = SolidColor(Color.Transparent),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                onValueChange = {
                    if (it.length <= 10) {
                        text = it
                    }
                },
                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                singleLine = true,
            )

            Spacer(modifier = Modifier.weight(1f))

            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .border(
                        1.dp,
                        colorResource(R.color.color_border_grey),
                        RoundedCornerShape(17.dp)
                    )
                    .align(Alignment.CenterVertically),

                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.card_background)
                )
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.nick_name_duplicate_check),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ContinueButton() {
    var sheetState by remember { mutableStateOf(false) }

    Button(
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        onClick = {
            sheetState = true
        },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, colorResource(R.color.button_border)),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.card_background),
            contentColor = colorResource(R.color.card_background)
        )
    ) {

        Text(
            text = stringResource(R.string.nick_name_continue),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.button_border),
                textAlign = TextAlign.Center
            )
        )
    }
}
