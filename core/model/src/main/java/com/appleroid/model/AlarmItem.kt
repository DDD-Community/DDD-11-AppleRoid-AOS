package com.appleroid.model

data class AlarmItem(
    val id: Int,
    val alarmType: String,
    val alarmImageRes: Int,
    val nickName: String,
    val mbti: String,
    val content: String,
    val time: String,
    val isComment: Boolean
)