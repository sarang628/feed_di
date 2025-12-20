package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.usecase.FeedWithPageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class FeedWithPageUseCaseImpl {
    @Provides
    fun provideFeedWithPageUseCase(
        feedLoadRepository: FeedLoadRepository,
    ): FeedWithPageUseCase {
        return object : FeedWithPageUseCase {
            override suspend fun invoke(page: Int) {
                feedLoadRepository.loadByPage(page)
            }
        }
    }
}