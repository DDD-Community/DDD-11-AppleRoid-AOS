package com.appleroid.core.data.di

import android.content.Context
import com.appleroid.core.data.ResourcesProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideResourcesProvider(@ApplicationContext context: Context): ResourcesProvider = ResourcesProvider(context)

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context = context
}
