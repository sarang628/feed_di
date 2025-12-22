package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.usecase.LoadFeedByReviewIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class LoadFeedByReviewIdUseCaseImpl {
    @Provides
    fun provideLoadFeedByReviewIdUseCase(
        feedLoadRepository: FeedLoadRepository,
    ): LoadFeedByReviewIdUseCase {
        return object : LoadFeedByReviewIdUseCase {
            override suspend fun invoke(reviewId: Int) {
                return feedLoadRepository.loadById(reviewId = reviewId)
            }
        }
    }
}