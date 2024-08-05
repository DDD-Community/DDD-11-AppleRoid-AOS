package com.appleroid.core.data.di

import com.appleroid.core.data.repository.FeedInfoListRepository
import com.appleroid.core.data.repository.FeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsFeedRepository(
        feedInfoListRepository: FeedInfoListRepository,
    ): FeedRepository
}
