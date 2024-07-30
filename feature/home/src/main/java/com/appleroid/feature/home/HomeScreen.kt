package com.appleroid.feature.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.MKungTabRow
import com.appleroid.core.designsystem.theme.BLACK
import com.appleroid.feature.home.model.FeedType
import kotlinx.coroutines.launch

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    //viewModel 구현 예정
    var myMbti by remember { mutableStateOf("ESTJ") }

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 2 }

    // 화면의 절반 너비를 계산, viewModel로 옮길 예정
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val halfScreenWidth = screenWidth / 2

    LaunchedEffect(
        pagerState.currentPage
    ) {

    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BLACK)
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Row (modifier = modifier.padding(top = 12.dp)){
                MKungTabRow(
                    tabWidth = halfScreenWidth,
                    feedTypes = listOf(stringResource(R.string.view_all), myMbti),
                    selectedTab = {
                        scope.launch {
                            if (pagerState.currentPage == FeedType.QUESTION.index){
                                pagerState.animateScrollToPage(it)
                            } else if (pagerState.currentPage == FeedType.MY_MBTI.index) {
                                pagerState.animateScrollToPage(it)
                            }
                        }
                    }
                )

                Spacer(modifier.weight(1f))

                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 20.dp),
                    painter = painterResource(R.drawable.ic_home_bell),
                    contentDescription = "bell"

                )
            }

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) {
                when(it) { //init
                    FeedType.QUESTION.index -> {
                        QuestionScreen()
                    }
                    FeedType.MY_MBTI.index -> {
                        MyMbtiScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Question",
            style = TextStyle(color = Color.White)
        )
    }
}

@Composable
fun MyMbtiScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "MyMbti",
            style = TextStyle(color = Color.White)
        )
    }
}