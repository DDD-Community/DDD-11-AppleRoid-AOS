package com.appleroid.feature.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appleroid.core.designsystem.component.EmptyContent
import com.appleroid.core.designsystem.component.TitleText
import com.appleroid.core.designsystem.component.VoteStatisticsRow
import com.appleroid.core.designsystem.component.WithTextCheckBoxCard
import com.appleroid.core.designsystem.theme.GREY03
import com.appleroid.core.designsystem.theme.GREY04
import com.appleroid.core.designsystem.theme.GREY05
import com.appleroid.core.designsystem.theme.GREY06
import com.appleroid.core.designsystem.theme.POINT01
import com.appleroid.core.designsystem.theme.WHITE
import com.appleroid.core.ui.FeedCard
import com.appleroid.core.ui.MKungTabRow
import com.appleroid.feature.home.model.FeedType
import com.appleroid.model.FeedInfo
import com.appleroid.model.FeedInfoItem
import com.appleroid.model.VoteStatistics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    reportBtnClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val halfScreenWidth by viewModel.halfScreenWidthState.collectAsState()

    HomeScreen(
        modifier = modifier,
        reportBtnClicked = reportBtnClicked,
        halfScreenWidth = halfScreenWidth,
        onScreenWidthChanged = viewModel::setScreenWidthChanged,
        coroutineScope = rememberCoroutineScope(),
        pagerState = rememberPagerState { 2 },
        feedInfo = viewModel.getFeedInfoItems(),
        voteStatics = viewModel.getVoteStatics()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    reportBtnClicked: () -> Unit,
    halfScreenWidth: Dp,
    feedInfo: FeedInfo,
    voteStatics: List<VoteStatistics>,
    onScreenWidthChanged: (Dp) -> Unit,
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val bottomSheetState = rememberModalBottomSheetState()
    val isMbtiResultSheetState = remember { mutableStateOf(false) }
    val isMoreSheetState = remember { mutableStateOf(false) }
    val showReportSelectScreen = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

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

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    FeedType.QUESTION.index -> {
                        QuestionScreen(
                            reportBtnClicked = reportBtnClicked,
                            feedInfoItems = feedInfo.feedInfoItems,
                            voteStatistics = voteStatics,
                            isMbtiResultSheetState = isMbtiResultSheetState,
                            isMoreSheetState = isMoreSheetState,
                            bottomSheetState = bottomSheetState,
                            showReportSelectScreen = showReportSelectScreen,
                            scope = scope
                        )
                    }
                    FeedType.MY_MBTI.index -> {
                        MyMbtiScreen()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    reportBtnClicked: () -> Unit,
    feedInfoItems: List<FeedInfoItem>,
    voteStatistics: List<VoteStatistics>,
    isMbtiResultSheetState: MutableState<Boolean>,
    isMoreSheetState: MutableState<Boolean>,
    showReportSelectScreen: MutableState<Boolean>,
    bottomSheetState: SheetState,
    scope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Crossfade(
            targetState = feedInfoItems.isEmpty(),
            label = "content empty"
        ) { isEmpty ->
            if (isEmpty) EmptyContent(title = stringResource(R.string.content_empty))
            else {
                FeedList(
                    feedInfoItems = feedInfoItems,
                    isMbtiResultSheetState = isMbtiResultSheetState,
                    isMoreSheetState = isMoreSheetState,
                    modifier = modifier
                )
            }
        }

        if (isMoreSheetState.value) {
            ReportBottomSheet(
                onDismiss = {
                    scope.launch {
                        isMoreSheetState.value = false
                    }
                },
                reportBtnClicked = reportBtnClicked,
                sheetState = bottomSheetState,
                showReportSelectScreen = showReportSelectScreen
            )
        }

        if (isMbtiResultSheetState.value) {
            MbtiResultBottomSheet(
                feedInfoItem = feedInfoItems.firstOrNull(),
                voteStatistics = voteStatistics,
                onDismiss = {
                    scope.launch {
                        isMbtiResultSheetState.value = false
                    }
                },
                sheetState = bottomSheetState
            )
        }

        if (showReportSelectScreen.value) {
            reportBtnClicked()
        }
    }
}

@Composable
fun MbtiResultScreen(
    feedInfoItem: FeedInfoItem,
    voteStatistics: List<VoteStatistics>,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        HeaderWithCloseButton(onClose)

        WithTextCheckBoxCard(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            text = feedInfoItem.buttonItems[0].title,
            isSelected = true,
            onSelected = {}
        )

        Spacer(modifier = Modifier.height(8.dp))

        WithTextCheckBoxCard(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            text = feedInfoItem.buttonItems[1].title,
            isSelected = false,
            onSelected = {}
        )

        Spacer(modifier = Modifier.height(28.dp))

        HorizontalDivider(color = GREY04)

        Spacer(modifier = Modifier.height(28.dp))

        VoteStatisticsList(voteStatistics = voteStatistics)

        Spacer(modifier = Modifier.height(32.dp))
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

@Composable
fun ReportButtonScreen(
    showReportSelectScreen: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 28.dp)
    ) {
        TextButton(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(
                    color = GREY03,
                    shape = RoundedCornerShape(12.dp)
                ),
            content = {
                Text(
                    text = stringResource(R.string.report),
                    style = MaterialTheme.typography.titleSmall,
                    color = WHITE
                )
            },
            onClick = {
                showReportSelectScreen.value = true
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MbtiResultBottomSheet(
    feedInfoItem: FeedInfoItem?,
    voteStatistics: List<VoteStatistics>,
    onDismiss: () -> Unit,
    sheetState: SheetState,
    modifier: Modifier = Modifier
) {
    if (feedInfoItem == null) return

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = GREY06,
        dragHandle = null
    ) {
        MbtiResultScreen(
            feedInfoItem = feedInfoItem,
            voteStatistics = voteStatistics,
            onClose = onDismiss,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportBottomSheet(
    onDismiss: () -> Unit,
    sheetState: SheetState,
    reportBtnClicked: () -> Unit,
    showReportSelectScreen: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = GREY06,
        dragHandle = { CustomDragHandle() }
    ) {
        ReportButtonScreen(
            showReportSelectScreen = showReportSelectScreen
        )
    }
}

@Composable
fun HeaderWithCloseButton(onClose: () -> Unit) {
    Row(
        modifier = Modifier.padding(vertical = 22.dp)
    ) {
        TitleText(
            modifier = Modifier
                .wrapContentSize()
                .height(27.dp),
            title = stringResource(R.string.mbti_result)
        )

        Spacer(modifier = Modifier.weight(1F))

        Image(
            modifier = Modifier
                .size(16.dp)
                .clickable { onClose() },
            painter = painterResource(R.drawable.ic_bottom_sheet_close),
            contentDescription = "Close button"
        )
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
fun CustomDragHandle() {
    Box (
        modifier = Modifier.padding(top = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .width(56.dp)
                .height(5.dp) // 핸들의 높이를 설정
                .clip(RoundedCornerShape(2.dp)) // 모서리를 둥글게 자름
                .background(GREY05)
        )
    }
}

@Composable
fun FeedList(
    feedInfoItems: List<FeedInfoItem>,
    isMbtiResultSheetState: MutableState<Boolean>,
    isMoreSheetState: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        items(feedInfoItems, key = { it.id }) { item ->
            val (oneSelected, onOneSelected) = remember { mutableStateOf(false) }
            val (twoSelected, onTwoSelected) = remember { mutableStateOf(false) }

            FeedCard(
                feedInfoItem = item,
                oneSelected = oneSelected,
                onOneSelected = onOneSelected,
                twoSelected = twoSelected,
                onTwoSelected = onTwoSelected,
                onMbtiResultClicked = {
                    isMbtiResultSheetState.value = true
                },
                onMoreClicked = {
                    isMoreSheetState.value = true
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun VoteStatisticsList(
    voteStatistics: List<VoteStatistics>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items(voteStatistics, key = { it.id }) { item ->
            val voteColor = if (item.id == 2) POINT01 else GREY03

            VoteStatisticsRow(
                mbtiType = item.mbti,
                voteColor = voteColor,
                votePercentage = item.votePercent,
                voteCount = item.voteCount,
                unitLabel = stringResource(R.string.unit_vote)
            )
        }
    }
}