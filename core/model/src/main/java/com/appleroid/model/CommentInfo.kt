package com.appleroid.model

data class CommentInfo(
    val totalComment: Int,
    val commentItems: List<CommentItem>
)

data class CommentItem(
    val id: Int,
    val nickName: String,
    val profileImageRes: Int,
    val mbti: String,
    val comment: String,
    val time: String,
    val subCommentCount: Int,
    val liked: Int,
    val replyItems: List<CommentReplyItem?>
)

data class CommentReplyItem(
    val id: Int,
    val nickName: String,
    val profileImageRes: Int,
    val mbti: String,
    val time: String,
    val liked: Int,
)