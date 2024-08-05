package com.appleroid.core.data.repository

import com.appleroid.core.data.model.FeedInfoDto
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    fun getFeedInfo(): Flow<FeedInfoDto> ///posts/:id
}