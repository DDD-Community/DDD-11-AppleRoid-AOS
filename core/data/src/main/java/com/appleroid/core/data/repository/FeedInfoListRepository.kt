package com.appleroid.core.data.repository

import com.appleroid.core.data.model.FeedInfoDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeedInfoListRepository @Inject constructor(

): FeedRepository {
    override fun getFeedInfo(): Flow<FeedInfoDto> {
        TODO("Not yet implemented")
    }

}