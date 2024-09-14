package com.appleroid.feature.ask

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.BackWithTextTopAppBar
import com.appleroid.core.designsystem.component.LabelText
import com.appleroid.core.designsystem.component.MKungBtn
import com.appleroid.core.designsystem.component.MKungTextField
import com.appleroid.core.designsystem.theme.BLACK
import com.appleroid.core.designsystem.theme.GREY02
import com.appleroid.core.designsystem.theme.GREY03
import com.appleroid.core.designsystem.theme.GREY05
import com.appleroid.core.designsystem.theme.POINT01
import com.appleroid.core.designsystem.theme.WHITE
import kotlinx.coroutines.launch

@Composable
fun AskRoute() {
    AskScreen()
}

@Composable
fun AskScreen() {
    var inputTitle by remember { mutableStateOf("") }
    var inputContent by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var bottomBtnEnable by remember { mutableStateOf(false) }

    val boxModifier = Modifier
        .fillMaxWidth()
        .background(color = GREY05, shape = RoundedCornerShape(12.dp))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BLACK)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            BackWithTextTopAppBar(
                height = 52.dp,
                title = stringResource(R.string.ask_add),
            )

            Spacer(modifier = Modifier.height(20.dp))

            InputField(
                text = inputTitle,
                onTextChanged = { inputTitle = it },
                placeholder = stringResource(R.string.ask_input_title),
                modifier = boxModifier.height(48.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                text = inputContent,
                onTextChanged = { inputContent = it },
                placeholder = stringResource(R.string.ask_input_content_free),
                modifier = boxModifier.height(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ToggleSwitch(
                isChecked = isChecked,
                onCheckedChange = { isChecked = it }
            )

            AnimatedVisibility(visible = isChecked) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    VoteTextField()
                    Spacer(modifier = Modifier.height(12.dp))
                    VoteTextField()
                }
            }
        }

        if (inputTitle.isNotBlank() && inputContent.isNotBlank()) {
            bottomBtnEnable = true
        } else bottomBtnEnable = true

        Box(
            modifier = Modifier.fillMaxSize() // 화면 전체를 채우는 Box
        ) {
            AnimatedVisibility(
                visible = inputTitle.isNotBlank() && inputContent.isNotBlank(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                MKungBtn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = 20.dp),
                    enable = bottomBtnEnable,
                    text = stringResource(R.string.registration),
                ) {

                }
            }
        }
    }
}

@Composable
fun InputField(
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopStart
    ) {
        MKungTextField(
            modifier = Modifier.padding(12.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            placeholder = placeholder,
            placeholderColor = GREY03,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            text = text,
            onChangedText = onTextChanged
        )
    }
}

@Composable
fun ToggleSwitch(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LabelText(
            modifier = Modifier.wrapContentSize(),
            text = stringResource(R.string.ask_vote),
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )

        Spacer(modifier = Modifier.weight(1F))

        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                uncheckedBorderColor = GREY02,
                uncheckedThumbColor = WHITE,
                uncheckedTrackColor = GREY02,
                checkedBorderColor = POINT01,
                checkedThumbColor = WHITE,
                checkedTrackColor = POINT01,

            ),
        )
    }
}

@Composable
fun VoteTextField() {
    var inputText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = GREY05,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.TopStart
    ) {
        MKungTextField(
            modifier = Modifier.padding(12.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            placeholder = stringResource(R.string.ask_input_vote),
            placeholderColor = GREY03,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            text = inputText,
            onChangedText = {
                inputText = it
            }
        )
    }
}
