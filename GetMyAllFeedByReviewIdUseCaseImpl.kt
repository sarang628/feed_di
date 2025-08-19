package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.GetMyAllFeedByReviewIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class GetMyAllFeedByReviewIdUseCaseImpl {
    @Provides
    fun provideGetMyAllFeedByReviewIdUseCase(
        feedRepository: FeedRepository,
    ): GetMyAllFeedByReviewIdUseCase {
        return object : GetMyAllFeedByReviewIdUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.loadMyAllFeedsByReviewId(reviewId)
            }
        }
    }
}