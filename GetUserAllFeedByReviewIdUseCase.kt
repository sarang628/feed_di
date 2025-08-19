package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.GetMyAllFeedByReviewIdUseCase
import com.sarang.torang.usecase.GetUserAllFeedByReviewIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class GetUserAllFeedByReviewIdUseCase {
    @Provides
    fun provideGetUserAllFeedByReviewIdUseCase(
        feedRepository: FeedRepository,
    ): com.sarang.torang.usecase.GetUserAllFeedByReviewIdUseCase {
        return object : GetUserAllFeedByReviewIdUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.loadUserAllFeedsByReviewId(reviewId)
            }
        }
    }

}