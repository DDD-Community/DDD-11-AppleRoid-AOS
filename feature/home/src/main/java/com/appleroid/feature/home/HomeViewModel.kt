package com.appleroid.feature.home

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appleroid.model.CommentInfo
import com.appleroid.model.CommentItem
import com.appleroid.model.CommentReplyItem
import com.appleroid.model.FeedButtonItem
import com.appleroid.model.FeedInfo
import com.appleroid.model.FeedInfoItem
import com.appleroid.model.VoteStatistics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // 화면 너비의 절반 값을 저장
    private val _halfScreenWidthState = MutableStateFlow(0.dp)
    val halfScreenWidthState: StateFlow<Dp> get() = _halfScreenWidthState

    // 화면 너비의 절반 값을 계산
    fun setScreenWidthChanged(screenWidthDp: Dp) {
        viewModelScope.launch {
            _halfScreenWidthState.value = screenWidthDp / 2
        }
    }

    fun getFeedInfoItems(): FeedInfo {
        return FeedInfo(
            myMbti = "ENFJ",
            listOf(
                FeedInfoItem(
                    id = 1,
                    nickName = "이상훈",
                    profileImageRes = R.drawable.ic_circle_test_2,
                    mbti = "ENFJ",
                    time = "10시간 전",
                    feedTitle = "테스트 메세지",
                    feedContent = "테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용 테스트 내용",
                    buttonItems = listOf(
                        FeedButtonItem("테스트 1"),
                        FeedButtonItem("테스트 2")
                    )
                ),
                FeedInfoItem(
                    id = 2,
                    nickName = "홍길동",
                    profileImageRes = R.drawable.ic_circle_test_1,
                    mbti = "INTP",
                    time = "2시간 전",
                    feedTitle = "MBTI 궁금해?",
                    feedContent = "궁금해 궁금해 궁금해 궁금해 궁금해 궁금해 궁금해 궁금해 궁금해",
                    buttonItems = listOf(
                        FeedButtonItem("궁금해 1"),
                        FeedButtonItem("궁금해 2")
                    )
                ),
                FeedInfoItem(
                    id = 3,
                    nickName = "옥동자",
                    profileImageRes = R.drawable.ic_circle_test_3,
                    mbti = "ISTJ",
                    time = "2시간 전",
                    feedTitle = "MBTI 의 역사는....",
                    feedContent = "테스트 데이터, 테스트 데이터, 테스트 데이터, 테스트 데이터, 테스트 데이터, 테스트 데이터, 테스트 데이터, 테스트 데이터",
                    buttonItems = listOf(
                        FeedButtonItem("데이터 123"),
                        FeedButtonItem("데이터 234")
                    )
                )
            )
        )
    }

    fun getVoteStatics(): List<VoteStatistics> {
        return listOf(
            VoteStatistics(id = 1, mbti = "ISTJ", votePercent = 20, voteCount = 200),
            VoteStatistics(id = 2, mbti = "ENFP", votePercent = 11, voteCount = 110),
            VoteStatistics(id = 3, mbti = "ESTJ", votePercent = 42, voteCount = 420),
            VoteStatistics(id = 4, mbti = "ENTP", votePercent = 5, voteCount = 50),
            VoteStatistics(id = 5, mbti = "ISTP", votePercent = 18, voteCount = 180),
            VoteStatistics(id = 6, mbti = "INFJ", votePercent = 4, voteCount = 40)
        )
    }

    fun getCommentInfo(): CommentInfo {
        return CommentInfo(
            totalComment = 3,
            commentItems = listOf(
                CommentItem(
                    id = 1,
                    nickName = "홍길동",
                    profileImageRes = R.drawable.ic_circle_test_1,
                    mbti = "ESTJ",
                    time =  "10시간 전",
                    comment = "홍길동은 동해 번쩍 서해 번쩍, 홍길동은 동해 번쩍 서해 번쩍, 홍길동은 동해 번쩍 서해 번쩍, 홍길동은 동해 번쩍 서해 번쩍",
                    liked = 193,
                    replyCount = 1,
                    replyItems = listOf(
                        CommentReplyItem(
                            id = 1,
                            nickName = "장길산",
                            profileImageRes = R.drawable.ic_circle_test_2,
                            mbti = "ENFP",
                            time = "6시간 전",
                            liked = 2
                        )
                    )
                ),
                CommentItem(
                    id = 2,
                    nickName = "돼지",
                    profileImageRes = R.drawable.ic_circle_test_2,
                    mbti = "ENTP",
                    time =  "6시간 전",
                    comment = "돼지는 꿀꿀꿀 돼지는 꿀꿀꿀 돼지는 꿀꿀꿀 돼지는 꿀꿀꿀 돼지는 꿀꿀꿀 돼지는 꿀꿀꿀 돼지는 꿀꿀꿀",
                    liked = 13,
                    replyCount = 3,
                    replyItems = listOf(
                        CommentReplyItem(
                            id = 1,
                            nickName = "토끼",
                            profileImageRes = R.drawable.ic_circle_test_3,
                            mbti = "ISTJ",
                            time = "4시간 전",
                            liked = 11
                        ),

                        CommentReplyItem(
                            id = 2,
                            nickName = "거북이",
                            profileImageRes = R.drawable.ic_circle_test_2,
                            mbti = "INTP",
                            time = "2시간 전",
                            liked = 42
                        ),

                        CommentReplyItem(
                            id = 3,
                            nickName = "용왕",
                            profileImageRes = R.drawable.ic_circle_test_3,
                            mbti = "ESTJ",
                            time = "1시간 전",
                            liked = 30
                        )
                    )
                ),
                CommentItem(
                    id = 3,
                    nickName = "외톨이",
                    profileImageRes = R.drawable.ic_circle_test_3,
                    mbti = "INFP",
                    comment = "외톨이 외톨이 외톨이 외톨이 외톨이 외톨이 외톨이",
                    time =  "19시간 전",
                    liked = 1,
                    replyCount = 0,
                    replyItems = listOf()
                ),
            )
        )
    }
}
