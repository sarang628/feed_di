package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.usecase.FeedRefreshUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class FeedRefreshUseCaseImpl {
    @Provides
    fun provideFeedRefreshUseCase(
        feedLoadRepository: FeedLoadRepository,
    ): FeedRefreshUseCase {
        return object : FeedRefreshUseCase {
            override suspend fun invoke() {
                feedLoadRepository.loadByPage(0)
            }
        }
    }
}