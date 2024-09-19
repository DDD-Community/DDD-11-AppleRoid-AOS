package com.appleroid.model

sealed class UserInfoRowItem(
    open val profileImageRes: Int,
    open val nickName: String,
    open val mbti: String,
    open val time: String
) {
    data class Feed(
        override val profileImageRes: Int,
        override val nickName: String,
        override val mbti: String,
        override val time: String = "",
        val onMoreClicked: () -> Unit = {}
    ) : UserInfoRowItem(profileImageRes, nickName, mbti, time)
    data class Alarm(
        override val profileImageRes: Int,
        override val nickName: String,
        override val mbti: String,
        override val time: String = "",
        val content: String = "",
        val isComment: Boolean = false
    ) : UserInfoRowItem(profileImageRes, nickName, mbti, time)
    data class Comment(
        override val profileImageRes: Int,
        override val nickName: String,
        override val mbti: String,
        override val time: String = "",
        val content: String = "",
        val liked: Int = 0,
        val reply: String = "",
        val onLikeSelected:(Int) -> Unit = {}
    ) : UserInfoRowItem(profileImageRes, nickName, mbti, time)
}
