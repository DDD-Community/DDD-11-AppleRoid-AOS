package com.appleroid.model

// /posts/:id/statics
data class VoteStatistics(
    val id: Int,
    val mbti: String,
    val votePercent: Int,
    val voteCount: Int
)