package com.appleroid.feature.alarm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.appleroid.model.AlarmItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun getAlarmList(isEmpty: Boolean = true): List<AlarmItem> {
        if (isEmpty) {
            return listOf(
                AlarmItem(
                    id = 1,
                    alarmType = "liked",
                    alarmImageRes = com.appleroid.core.designsystem.R.drawable.ic_select_check,
                    nickName = "강연",
                    mbti = "ESTP",
                    content = "강연님이 댓글을 좋아합니다.",
                    time = "3시간 전",
                    isComment = false
                ),
                AlarmItem(
                    id = 2,
                    alarmType = "replay",
                    alarmImageRes = com.appleroid.core.designsystem.R.drawable.ic_select_check,
                    nickName = "기창",
                    mbti = "ENFJ",
                    content = "저는 이렇게 생각해요",
                    time = "10분 전",
                    isComment = true
                ),
                AlarmItem(
                    id = 3,
                    alarmType = "liked",
                    alarmImageRes = com.appleroid.core.designsystem.R.drawable.ic_select_check,
                    nickName = "종민",
                    mbti = "INFP",
                    content = "종민님이 댓글을 좋아합니다.",
                    time = "3시간 전",
                    isComment = false
                ),
                AlarmItem(
                    id = 4,
                    alarmType = "replay",
                    alarmImageRes = com.appleroid.core.designsystem.R.drawable.ic_select_check,
                    nickName = "현수",
                    mbti = "ENFP",
                    content = "상훈님 조심하세요",
                    time = "5분 전",
                    isComment = true
                ),
            )
        } else return emptyList()
    }
}