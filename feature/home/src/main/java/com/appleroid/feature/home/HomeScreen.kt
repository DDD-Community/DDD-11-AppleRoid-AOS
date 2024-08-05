package com.appleroid.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.appleroid.core.designsystem.component.DottedDivider
import com.appleroid.core.designsystem.component.ImageWithTextBtn
import com.appleroid.core.designsystem.theme.BLACK
import com.appleroid.core.designsystem.theme.GREY06
import com.appleroid.core.ui.FeedCard
import com.appleroid.core.ui.MKungTabRow
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
                    contentDescription = "alarm bell image",
                    painter = painterResource(R.drawable.ic_home_bell),
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
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp),
        /*verticalArrangement = Arrangement.spacedBy(24.dp)*/
    ) {
        items(2) { index ->
            FeedCard(
                modifier = modifier,
                profileImageRes = R.drawable.ic_circle_test_2,
                moreImageRes = R.drawable.ic_home_contents_more,
                nickName = "박종민",
                mbti = "ENFP",
                time = "3시간 전",
                feedTitle = "나는 회가 정말 좋아",
                feedContent =
                "광어가 좋아? 방어가 좋아? 숭어가 좋아? 연어가 좋아? 참돔이 좋아? 상어가 좋아? 다금바리가 좋아? 가오리가 좋아? 홍어가 좋아? 갈치가 좋아? " +
                        "고등어가 좋아? 꽁치가 좋아? 넙치가 좋아? 대구가 좋아? 도다리가 좋아? 민어가 좋아? 삼치가 좋아? 서대가 좋아? 아귀가 좋아? 임연수어가 좋아? " +
                        "전어가 좋아? 조기가 좋아? 청어가 좋아? 참치가 좋아? 복어가 좋아? 붕어가 좋아? 피라미가 좋아? 송어가 좋아? 무지개송어가 좋아? 돌돔이 좋아? " +
                        "우럭이 좋아? 쏨뱅이가 좋아? 동태가 좋아? 방어가 좋아? 바다가재가 좋아? 전복이 좋아? 삼치가 좋아? 줄무늬감펭이 좋아? 부시리가 좋아?",
                buttonTitleList = mutableListOf("나는 회 싫어", "고기가 더 좋아"),
                buttonPercentList = mutableListOf(49, 51),
                boxRow = {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {

                        DottedDivider()

                        Row(
                            modifier = modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_eclipse_start),
                                contentDescription = "eclipse start image"
                            )

                            Spacer(modifier.weight(1F))

                            Image(
                                painter = painterResource(R.drawable.ic_eclipse_end),
                                contentDescription = "eclipse end image"
                            )
                        }
                    }
                },
                resultRow = {
                    ImageWithTextBtn(
                        modifier = modifier,
                        selectedImageRes = R.drawable.ic_plus,
                        unselectedImageRes = R.drawable.ic_plus,
                        text = stringResource(R.string.mbti_result),
                        interval = 2.dp,
                        arrangement = Arrangement.End,
                        onClick = {

                        }
                    )
                },
                snsRow = {
                    ImageWithTextBtn(
                        modifier = modifier.weight(weight = 1F, fill = false),
                        selectedImageRes = R.drawable.ic_like,
                        unselectedImageRes = R.drawable.ic_un_like,
                        text = "145",
                        interval = 8.dp,
                        arrangement = Arrangement.Start,
                        onClick = {

                        }
                    )

                    ImageWithTextBtn(
                        modifier = modifier.weight(weight = 1F, fill = false),
                        selectedImageRes = R.drawable.ic_comment,
                        unselectedImageRes = R.drawable.ic_comment,
                        text = "37",
                        interval = 8.dp,
                        arrangement = Arrangement.Center,
                        onClick = {

                        }
                    )

                    ImageWithTextBtn(
                        modifier = modifier.weight(weight = 1F, fill = false),
                        selectedImageRes = R.drawable.ic_vote,
                        unselectedImageRes = R.drawable.ic_vote,
                        text = "705표",
                        interval = 8.dp,
                        arrangement = Arrangement.End,
                        onClick = {

                        }
                    )
                }
            )
        }
    }
}

@Composable
fun MyMbtiScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

    }
}