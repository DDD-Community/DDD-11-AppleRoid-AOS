package com.appleroid.feature.home

import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appleroid.core.data.ResourcesProvider
import com.appleroid.model.FeedButtonItem
import com.appleroid.model.FeedCardResources
import com.appleroid.model.FeedInfo
import com.appleroid.model.FeedInfoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val resourcesProvider: ResourcesProvider
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
                        FeedButtonItem("테스트 1", "24"),
                        FeedButtonItem("테스트 2", "76")
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
                        FeedButtonItem("궁금해 1", "44"),
                        FeedButtonItem("궁금해 2", "56")
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
                        FeedButtonItem("데이터 123", "14"),
                        FeedButtonItem("데이터 234", "86")
                    )
                )
            )
        )
    }

    fun getFeedCardResources(): FeedCardResources {
        return FeedCardResources(
            moreImageRes = resourcesProvider.getDrawableId(R.drawable.ic_home_contents_more),
            eclipseStartImageRes = resourcesProvider.getDrawableId(R.drawable.ic_eclipse_start),
            eclipseEndImageRes = resourcesProvider.getDrawableId(R.drawable.ic_eclipse_end),
            plusImageRes = resourcesProvider.getDrawableId(R.drawable.ic_plus),
            likeImageRes = resourcesProvider.getDrawableId(R.drawable.ic_like),
            unLikeImageRes = resourcesProvider.getDrawableId(R.drawable.ic_un_like),
            commentImageRes = resourcesProvider.getDrawableId(R.drawable.ic_comment),
            voteImageRes = resourcesProvider.getDrawableId(R.drawable.ic_vote),
            mbtiStringRes = resourcesProvider.getString(R.string.mbti_result)
        )
    }
}
