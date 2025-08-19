package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FeedRepository
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
        feedRepository: FeedRepository,
    ): FeedWithPageUseCase {
        return object : FeedWithPageUseCase {
            override suspend fun invoke(page: Int) {
                feedRepository.loadFeedWithPage(page)
            }
        }
    }
}