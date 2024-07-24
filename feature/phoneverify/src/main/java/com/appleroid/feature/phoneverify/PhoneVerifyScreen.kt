package com.appleroid.feature.phoneverify

import VerificationBottomSheet
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
            .systemBarsPadding()
            .padding(bottom = 47.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopLayer()
            NotificationTitles()
            InfoInputCard()

            Spacer(modifier = Modifier.weight(1f))

            PhoneVerifyButton()
        }
    }
}

@Composable
fun TopLayer() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp),
        text = stringResource(R.string.title),
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
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 30.dp),
            text = stringResource(R.string.welcome_message),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Start
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.verification_message),
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
fun InfoInputCard() {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .background(
                color = colorResource(R.color.card_background),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(start = 24.dp, end = 24.dp, top = 18.dp, bottom = 18.dp)
    ) {
        Column {
            InputField(
                label = stringResource(R.string.card_phone_number_title),
                placeholder = stringResource(R.string.card_telecom),
                keyboardType = KeyboardType.NumberPassword
            )
            Spacer(modifier = Modifier.height(11.dp))

            InputField(
                label = stringResource(R.string.card_name_title),
                keyboardType = KeyboardType.Text
            )
        }
    }
}

@Composable
fun InputField(
    label: String,
    placeholder: String = "",
    keyboardType: KeyboardType
) {
    var text by remember { mutableStateOf("") }

    Column {
        Text(
            modifier = Modifier.height(18.dp),
            text = label,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.color_grey01),
                textAlign = TextAlign.Start
            )
        )
        Spacer(modifier = Modifier.height(9.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (placeholder.isNotEmpty()) {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                )
                Image(
                    modifier = Modifier
                        .padding(start = 4.dp),
                    painter = painterResource(R.drawable.ic_polygon),
                    contentDescription = null
                )
            }
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .background(Color.Transparent),
                value = text,
                cursorBrush = SolidColor(Color.Transparent),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                onValueChange = { text = it },
                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                singleLine = true
            )
        }
        Spacer(
            modifier = Modifier
                .padding(top = 10.dp)
                .height(1.dp)
                .fillMaxWidth()
                .background(colorResource(R.color.color_grey04))
        )
    }
}

@Composable
fun PhoneVerifyButton() {
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
            text = stringResource(R.string.verification),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.button_border),
                textAlign = TextAlign.Center
            )
        )
    }

    VerificationBottomSheet(
        sheetState = sheetState,
        setSheetState = { newState ->
        sheetState = newState
    })
}
