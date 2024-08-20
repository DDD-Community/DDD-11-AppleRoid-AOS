package com.appleroid.model

data class FeedInfo(
    val myMbti: String,
    val feedInfoItems: List<FeedInfoItem>
)

data class FeedButtonItem(
    val title: String
)

data class FeedInfoItem(
    val id: Int,
    val nickName: String,
    val profileImageRes: Int,
    val mbti: String,
    val time: String,
    val feedTitle: String,
    val feedContent: String,
    val buttonItems: List<FeedButtonItem>
)