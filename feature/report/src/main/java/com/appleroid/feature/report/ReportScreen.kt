package com.appleroid.feature.report

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appleroid.core.designsystem.component.MKungBtn
import com.appleroid.core.designsystem.component.MKungTextField
import com.appleroid.core.designsystem.component.TitleText
import com.appleroid.core.designsystem.component.WithTextCheckBox
import com.appleroid.core.designsystem.component.WithTextCheckBoxCard
import com.appleroid.core.designsystem.theme.BLACK
import com.appleroid.core.designsystem.theme.BTN_BACKGROUND
import com.appleroid.core.designsystem.theme.GREY01
import com.appleroid.core.designsystem.theme.GREY03
import com.appleroid.core.designsystem.utils.PhoneNumberVisualTransformation
import com.appleroid.core.ui.FeedCard
import com.appleroid.model.Report
import kotlinx.coroutines.launch

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
    val lastItemId = reportList.lastOrNull { it.title == "기타" }?.id ?: - 1

    Box(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopBar(backBtnClicked = backBtnClicked)

            TitleText(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 20.dp, top = 16.dp),
                title = stringResource(R.string.report_reason_select)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 20.dp, end = 20.dp, top = 18.dp)
            ) {
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
            }

            // 마지막 항목이 선택된 경우에만 TitleText 표시
            if (selectedId == lastItemId) {
                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier.padding(horizontal = 20.dp)
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
                            onChangedText = { newValue ->
                                reportComment = newValue
                            },
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1F))

            MKungBtn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 20.dp),
                enable = selectedId != 0 &&
                        (selectedId != lastItemId || reportComment.isNotBlank()),
                // 선택된 ID가 0이 아니며, 기타 항목이 선택된 경우나 reportComment 가 비어있지 않으면 활성화
                text = stringResource(R.string.submit)
            ) {

            }

            Spacer(modifier = Modifier.height(28.dp))
        }
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