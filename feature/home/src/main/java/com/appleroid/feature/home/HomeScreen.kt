package com.appleroid.feature.home

import android.widget.Toast
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
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appleroid.core.designsystem.component.CircleImage
import com.appleroid.core.designsystem.component.CustomDragHandle
import com.appleroid.core.designsystem.component.EmptyContent
import com.appleroid.core.designsystem.component.LabelText
import com.appleroid.core.designsystem.component.SendTextField
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
import com.appleroid.core.ui.ReportButtonSheet
import com.appleroid.core.ui.UserInfoRow
import com.appleroid.feature.home.model.FeedType
import com.appleroid.model.CommentInfo
import com.appleroid.model.CommentItem
import com.appleroid.model.FeedInfoItem
import com.appleroid.model.UserInfoRowItem
import com.appleroid.model.VoteStatistics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    reportBtnClicked: () -> Unit,
    alarmBtnClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    HomeScreen(
        modifier = modifier,
        reportBtnClicked = reportBtnClicked,
        alarmBtnClicked = alarmBtnClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    reportBtnClicked: () -> Unit,
    alarmBtnClicked: () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    pagerState: PagerState = rememberPagerState { 2 },
    modifier: Modifier = Modifier
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val bottomSheetState = rememberModalBottomSheetState()

    val halfScreenWidth by viewModel.halfScreenWidthState.collectAsState()

    var isMbtiResultSheet by remember { mutableStateOf(false) }
    var isMoreSheet by remember { mutableStateOf(false) }
    var isReportSelectScreen by remember { mutableStateOf(false) }
    var isCommentSheet by remember { mutableStateOf(false) }

    var comment by remember { mutableStateOf("") }

    val feedInfo = viewModel.getFeedInfoItems()
    val voteStatics = viewModel.getVoteStatics()
    val commentInfo = viewModel.getCommentInfo()

    LaunchedEffect(screenWidth) {
        viewModel.setScreenWidthChanged(screenWidth)
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
                pagerState = pagerState,
                alarmBtnClicked = alarmBtnClicked
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
                            isMbtiResultSheet = isMbtiResultSheet,
                            onChangeMbtiResultSheet = {
                                isMbtiResultSheet = it
                            },
                            isMoreSheet = isMoreSheet,
                            onChangeMoreSheet = {
                                isMoreSheet = it
                            },
                            comment = comment,
                            onChangeComment = {
                                comment = it
                            },
                            bottomSheet = bottomSheetState,
                            isCommentSheet = isCommentSheet,
                            onChangeCommentSheet = {
                                isCommentSheet = it
                            },
                            commentInfo = commentInfo,
                            isReportSelectScreen = isReportSelectScreen,
                            onChangeReportSelectScreen = {
                                isReportSelectScreen = true
                            },
                            coroutineScope = coroutineScope
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
    isMbtiResultSheet: Boolean,
    onChangeMbtiResultSheet: (Boolean) -> Unit,
    isMoreSheet: Boolean,
    onChangeMoreSheet: (Boolean) -> Unit,
    isReportSelectScreen: Boolean,
    onChangeReportSelectScreen: () -> Unit,
    isCommentSheet: Boolean,
    onChangeCommentSheet: (Boolean) -> Unit,
    comment: String,
    onChangeComment: (String) -> Unit,

    feedInfoItems: List<FeedInfoItem>,
    voteStatistics: List<VoteStatistics>,
    commentInfo: CommentInfo,

    bottomSheet: SheetState,
    coroutineScope: CoroutineScope,
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
                    onChangeMbtiResultSheet = onChangeMbtiResultSheet,
                    onChangeMoreSheet = onChangeMoreSheet,
                    onChangeCommentSheet = onChangeCommentSheet,
                    modifier = modifier,
                )
            }
        }

        if (isMoreSheet) {
            ReportBottomSheet(
                onDismiss = {
                    coroutineScope.launch {
                        onChangeMoreSheet(false)
                    }
                },
                sheetState = bottomSheet,
                onChangeShowReportSelectScreen = onChangeReportSelectScreen
            )
        }

        if (isMbtiResultSheet) {
            MbtiResultBottomSheet(
                feedInfoItem = feedInfoItems.firstOrNull(),
                voteStatistics = voteStatistics,
                onDismiss = {
                    coroutineScope.launch {
                        onChangeMbtiResultSheet(false)
                    }
                },
                sheetState = bottomSheet
            )
        }

        if (isCommentSheet) {
            CommentBottomSheet(
                sheetState = bottomSheet,
                comment = comment,
                onChangeComment = onChangeComment,
                commentInfo = commentInfo,
                onDismiss = {
                    onChangeCommentSheet(false)
                }
            )
        }

        if (isReportSelectScreen) {
            reportBtnClicked()
            onChangeReportSelectScreen()
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
fun CommentScreen(
    comment: String,
    onChangeComment: (String) -> Unit,
    commentUnit: Int,
    commentItems: List<CommentItem>,
    profileImageRes: Int,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Box {
        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 94.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.padding(start = 20.dp, bottom = 16.dp)
                ) {
                    LabelText(
                        modifier = Modifier.height(19.dp),
                        text = stringResource(com.appleroid.core.ui.R.string.feature_home_feed_comment),
                        style = MaterialTheme.typography.bodyLarge,
                        color = WHITE
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    LabelText(
                        modifier = Modifier.height(19.dp),
                        text = "$commentUnit",
                        style = MaterialTheme.typography.bodyLarge,
                        color = WHITE
                    )
                }

                Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(GREY05))
            }

            items(commentItems, key = { it.id }) { item ->
                var like by remember { mutableIntStateOf(item.liked) }

                UserInfoRow(
                    userInfoRowItem = UserInfoRowItem.Comment(
                        profileImageRes = item.profileImageRes,
                        nickName = item.nickName,
                        content = item.comment,
                        mbti = item.mbti,
                        liked = like,
                        time = item.time,
                        reply = if (item.replyCount == 0) stringResource(R.string.comment_write) else stringResource(R.string.comment_show, item.replyCount),
                        onLikeSelected = {
                            like = it
                        }
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(94.dp)
                .background(GREY06)
                .align(Alignment.BottomCenter)
                .imePadding() // 키보드가 올라올 때 뷰도 함께 올라옴
        ) {
            Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(GREY04))

            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            ) {
                CircleImage(
                    modifier = Modifier
                        .width(36.dp)
                        .height(36.dp),
                    imageRes = profileImageRes,
                    contentDescription = "user profile image"
                )

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(
                            color = GREY05,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    SendTextField(
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                        btnImageRes = R.drawable.ic_input_send,
                        placeholder = stringResource(R.string.comment_add, "홍길동"),
                        placeholderColor = GREY03,
                        text = comment,
                        onChangeText = {
                            onChangeComment(it)
                        },
                        onSendBtnClicked = {
                            Toast.makeText(context, "댓글 등록 완료 !", Toast.LENGTH_SHORT).show()
                            onChangeComment("")
                        }
                    )
                }
            }
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

@Composable
fun ReportButtonScreen(
    onChangeShowReportSelectScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    ReportButtonSheet(
        onChangeShowReportSelectScreen = onChangeShowReportSelectScreen
    )
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
    onChangeShowReportSelectScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = GREY06,
        dragHandle = { CustomDragHandle() }
    ) {
        ReportButtonScreen(
            onChangeShowReportSelectScreen = onChangeShowReportSelectScreen
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentBottomSheet(
    onDismiss: () -> Unit,
    sheetState: SheetState,
    comment: String,
    onChangeComment: (String) -> Unit,
    commentInfo: CommentInfo,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = GREY06,
        dragHandle = { CustomDragHandle() }
    ) {
        CommentScreen(
            comment = comment,
            onChangeComment = onChangeComment,
            commentUnit = commentInfo.totalComment,
            commentItems = commentInfo.commentItems,
            profileImageRes = R.drawable.ic_circle_test_2
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
    pagerState: PagerState,
    alarmBtnClicked: () -> Unit
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
                .padding(end = 20.dp)
                .clickable {
                    alarmBtnClicked()
                },
            contentDescription = "alarm bell image",
            painter = painterResource(R.drawable.ic_home_bell)
        )
    }
}

@Composable
fun FeedList(
    feedInfoItems: List<FeedInfoItem>,
    onChangeMbtiResultSheet: (Boolean) -> Unit,
    onChangeMoreSheet: (Boolean) -> Unit,
    onChangeCommentSheet: (Boolean) -> Unit,
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
                    onChangeMbtiResultSheet(true)
                },
                onMoreClicked = {
                    onChangeMoreSheet(true)
                },
                likeClicked = {

                },
                commentClicked = {
                    onChangeCommentSheet(true)
                },
                voteClicked = {

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