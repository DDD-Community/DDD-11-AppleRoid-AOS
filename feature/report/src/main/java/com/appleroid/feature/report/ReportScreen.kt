package com.appleroid.feature.report

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appleroid.core.designsystem.component.MKungTextField
import com.appleroid.core.designsystem.component.TitleText
import com.appleroid.core.designsystem.component.WithTextCheckBox
import com.appleroid.core.designsystem.theme.BLACK
import com.appleroid.core.designsystem.theme.BTN_BACKGROUND
import com.appleroid.core.designsystem.theme.DOT
import com.appleroid.core.designsystem.theme.GREY03
import com.appleroid.core.designsystem.theme.GREY05
import com.appleroid.core.designsystem.theme.POINT01
import com.appleroid.model.Report

@Composable
fun ReportRoute(
    backBtnClicked: () -> Unit,
    viewModel: ReportViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    ReportScreen(
        backBtnClicked = backBtnClicked,
        reportList = viewModel.getReportList(),
        modifier = modifier,
    )
}

@Composable
fun ReportScreen(
    backBtnClicked: () -> Unit,
    reportList: List<Report>,
    modifier: Modifier = Modifier
) {
    var selectedId by remember { mutableIntStateOf(0) }
    var reportComment by remember { mutableStateOf("") }
    val lastItemId = reportList.lastOrNull { it.title == "기타" }?.id ?: -1

    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    val enableSubmit = selectedId != 0 && (selectedId != lastItemId || reportComment.isNotBlank())

    Box(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TopBar(backBtnClicked = backBtnClicked)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                item {
                    TitleText(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 18.dp),
                        title = stringResource(R.string.report_reason_select)
                    )
                }
                items(reportList, key = { it.id }) { item ->
                    WithTextCheckBox(
                        modifier = Modifier
                            .height(48.dp)
                            .fillMaxWidth(),
                        text = item.title,
                        isSelected = selectedId == item.id,
                        onSelected = {
                            selectedId = if (it) item.id else 0
                        }
                    )
                }

                if (selectedId == lastItemId) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        ReportCommentInput(
                            reportComment = reportComment,
                            onCommentChange = { reportComment = it }
                        )
                    }
                }
            }
        }

        SubmitButton(
            isEnabled = enableSubmit,
            isKeyboardVisible = isKeyboardVisible,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(
                    start = if (isKeyboardVisible) 0.dp else 20.dp,
                    end = if (isKeyboardVisible) 0.dp else 20.dp,
                    bottom = if (isKeyboardVisible) 0.dp else 28.dp
                )
        )
    }
}

@Composable
fun ReportCommentInput(
    reportComment: String,
    onCommentChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.height(120.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(BTN_BACKGROUND)
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            MKungTextField(
                textStyle = MaterialTheme.typography.bodySmall.copy(color = Color.White),
                placeholder = stringResource(R.string.report_reason_input),
                placeholderColor = GREY03,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                text = reportComment,
                onChangedText = onCommentChange
            )
        }
    }
}

@Composable
fun SubmitButton(
    isEnabled: Boolean,
    isKeyboardVisible: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    TextButton(
        modifier = modifier
            .height(48.dp)
            .background(
                color = if (isEnabled) POINT01 else GREY05,
                shape = if (isKeyboardVisible) RoundedCornerShape(0.dp) else RoundedCornerShape(16.dp)
            ),
        enabled = isEnabled,
        onClick = onClick
    ) {
        Text(
            text = stringResource(R.string.submit),
            style = MaterialTheme.typography.titleSmall,
            color = if (isEnabled) BLACK else DOT
        )
    }
}


@Composable
fun TopBar(
    backBtnClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)
                .clickable {
                    backBtnClicked()
                },
            contentDescription = "alarm bell image",
            painter = painterResource(R.drawable.ic_back),
        )

        TitleText(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
                .align(Alignment.Center),
            title = stringResource(R.string.report)
        )
    }
}