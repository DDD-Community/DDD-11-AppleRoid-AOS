package com.appleroid.feature.home

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
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appleroid.core.ui.FeedCard
import com.appleroid.core.ui.MKungTabRow
import com.appleroid.feature.home.model.FeedType
import com.appleroid.model.FeedButtonItem
import com.appleroid.model.FeedCardResources
import com.appleroid.model.FeedInfo
import com.appleroid.model.FeedInfoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val halfScreenWidth by viewModel.halfScreenWidthState.collectAsState()

    HomeScreen(
        modifier = modifier,
        halfScreenWidth = halfScreenWidth,
        onScreenWidthChanged = viewModel::setScreenWidthChanged,
        coroutineScope = rememberCoroutineScope(),
        pagerState = rememberPagerState { 2 },
        feedInfo = viewModel.getFeedInfoItems()
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    halfScreenWidth: Dp,
    feedInfo: FeedInfo,
    onScreenWidthChanged: (Dp) -> Unit,
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    LaunchedEffect(screenWidth) {
        onScreenWidthChanged(screenWidth)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            TopBar(
                halfScreenWidth = halfScreenWidth,
                myMbti = feedInfo.myMbti,
                coroutineScope = coroutineScope,
                pagerState = pagerState
            )
            ContentPager(
                feedInfoItems = feedInfo.feedInfoItems,
                pagerState = pagerState
            )
        }
    }
}

@Composable
fun TopBar(
    halfScreenWidth: Dp,
    myMbti: String,
    coroutineScope: CoroutineScope,
    pagerState: PagerState
) {
    Row(modifier = Modifier.padding(top = 12.dp)) {
        MKungTabRow(
            tabWidth = halfScreenWidth,
            feedTypes = listOf(stringResource(R.string.view_all), myMbti),
            selectedTab = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(it)
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 20.dp),
            contentDescription = "alarm bell image",
            painter = painterResource(R.drawable.ic_home_bell),
        )
    }
}

@Composable
fun ContentPager(
    feedInfoItems: List<FeedInfoItem>,
    pagerState: PagerState
) {
    HorizontalPager(
        state = pagerState,
        userScrollEnabled = false
    ) { page ->
        when (page) {
            FeedType.QUESTION.index -> {
                QuestionScreen(feedInfoItems = feedInfoItems)
            }
            FeedType.MY_MBTI.index -> {
                MyMbtiScreen()
            }
        }
    }
}

@Composable
fun QuestionScreen(
    feedInfoItems: List<FeedInfoItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp),
    ) {
        items(feedInfoItems, key = { it.id }) { item ->
            val (buttonOneSelected, onButtonOneSelectedChange) = remember { mutableStateOf(false) }
            val (buttonTwoSelected, onButtonTwoSelectedChange) = remember { mutableStateOf(false) }

            FeedCard(
                feedInfoItem = item,
                buttonOneSelected = buttonOneSelected,
                onButtonOneSelectedChange = onButtonOneSelectedChange,
                buttonTwoSelected = buttonTwoSelected,
                onButtonTwoSelectedChange = onButtonTwoSelectedChange,
                modifier = modifier
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