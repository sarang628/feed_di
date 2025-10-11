package com.sarang.torang.di.feed_di

import com.sarang.torang.di.repository.FeedRepositoryImpl
import com.sarang.torang.usecase.DeleteAllFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DeleteAllFeedUseCaseImpl {

    @Provides
    fun provideDeleteAllFeedUseCase(
        feedRepositoryImpl: FeedRepositoryImpl
    ): DeleteAllFeedUseCase {
        return object : DeleteAllFeedUseCase {
            override suspend fun invoke() {
                feedRepositoryImpl.deleteAll()
            }
        }
    }
}