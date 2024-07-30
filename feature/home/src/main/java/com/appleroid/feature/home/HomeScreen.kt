package com.appleroid.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.MKungTabRow
import com.appleroid.core.designsystem.theme.BLACK
import com.appleroid.feature.home.model.FeedType

@Composable
fun HomeRoute(){
    HomeScreen()
}

@Composable
fun HomeScreen() {
    //viewModel 구현 예정
    var feedType by remember { mutableStateOf(FeedType.QUESTION) }
    var myMbti by remember { mutableStateOf("ESTJ") }

    // 화면의 절반 너비를 계산, viewModel로 옮길 예정
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val halfScreenWidth = screenWidth / 2

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        MKungTabRow(
            tabWidth = halfScreenWidth,
            feedTypes = listOf(stringResource(R.string.view_all), myMbti),
            selectedTab = {
                when(it) {
                    0 -> feedType = FeedType.QUESTION
                    1 -> feedType = FeedType.MY_MBTI
                }
            }
        )
    }
}