package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.repository.FeedRepository
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
    ): GetUserAllFeedByReviewIdUseCase {
        return object : GetUserAllFeedByReviewIdUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.findAllUserFeedById(reviewId)
            }
        }
    }

}