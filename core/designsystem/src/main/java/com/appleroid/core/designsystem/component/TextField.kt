package com.appleroid.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.theme.BLACK
import com.appleroid.core.designsystem.theme.BTN_BACKGROUND
import com.appleroid.core.designsystem.theme.GREY01
import com.appleroid.core.designsystem.theme.POINT01
import com.appleroid.core.designsystem.theme.WHITE
import com.appleroid.core.designsystem.utils.toDp

@Composable
fun MKungTextField(
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    placeholder : String = "",
    placeholderColor : Color = GREY01,
    isLimitWidth : Boolean = false,
    focusRequester: FocusRequester = remember { FocusRequester() },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation : VisualTransformation = VisualTransformation.None,
    text : String,
    onChangedText : (String) -> Unit
){
    var textFieldWidth by remember { mutableStateOf(0.dp) }

    Box(
        modifier = modifier
    ){
        if(text.isBlank()){
            Text(
                text = placeholder,
                color = placeholderColor,
                style = textStyle,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .onGloballyPositioned {
                        textFieldWidth = it.size.width.toDp() + 3.dp
                    }
            )
        }

        BasicTextField(
            value = text,
            onValueChange = onChangedText,
            textStyle = textStyle,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .focusRequester(focusRequester)
                .then(
                    if(isLimitWidth) Modifier.width(textFieldWidth)
                    else Modifier.wrapContentWidth()
                )
        )
    }
}

@Composable
fun CheckTextField(
    modifier: Modifier = Modifier,
    showTimer : Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    btnImageRes : Int,
    label : String,
    text : String,
    onChangeText : (String) -> Unit
){
    Column(modifier = modifier.fillMaxWidth()) {
        LabelText(
            text = label,
            color = GREY01,
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .background(
                    color = BTN_BACKGROUND,
                    shape = RoundedCornerShape(12.dp)
                )
        ){
            MKungTextField(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 15.dp),
                textStyle = MaterialTheme.typography.labelLarge,
                keyboardOptions = keyboardOptions,
                text = text,
                onChangedText = onChangeText
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 15.dp)
            ){
                if(showTimer){
                    LabelText(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "03:00",
                        color = POINT01,
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Image(
                    painter = painterResource(btnImageRes),
                    contentDescription = "resend",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}
