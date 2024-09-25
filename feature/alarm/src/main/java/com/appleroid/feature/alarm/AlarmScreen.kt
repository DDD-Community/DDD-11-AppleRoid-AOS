package com.appleroid.feature.alarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appleroid.core.designsystem.component.BackWithTextTopAppBar
import com.appleroid.core.designsystem.theme.BLACK
import com.appleroid.core.ui.UserInfoRow
import com.appleroid.model.UserInfoRowItem

@Composable
fun AlarmRoute() {
    AlarmScreen()
}

@Composable
fun AlarmScreen(
    viewModel: AlarmViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BLACK)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        viewModel.getAlarmList().let { list ->
            if (list.isEmpty()) {
                //emptyView()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp, bottom = 94.dp)
                ) {
                    item {
                        BackWithTextTopAppBar(
                            height = 52.dp,
                            title = stringResource(R.string.alarm_title)
                        )
                    }
                    items(list, key = { it.id }) { item ->
                        UserInfoRow(
                            userInfoRowItem = UserInfoRowItem.Alarm(
                                profileImageRes = item.alarmImageRes,
                                nickName = item.nickName,
                                mbti = item.mbti,
                                content = item.content,
                                time = item.time,
                                isComment = item.isComment
                            )
                        )
                    }
                }
            }
        }
    }
}