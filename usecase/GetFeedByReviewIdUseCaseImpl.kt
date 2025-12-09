package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.data.feed.Feed
import com.sarang.torang.di.feed_di.toFeedData
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.GetFeedByReviewIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class GetFeedByReviewIdUseCaseImpl {
    @Provides
    fun provideGetFeedByReviewIdUseCase(
        feedRepository: FeedRepository,
    ): GetFeedByReviewIdUseCase {
        return object : GetFeedByReviewIdUseCase {
            override suspend fun invoke(reviewId: Int?): Feed {
                if(reviewId == null) return Feed.Companion.Empty
                return feedRepository.findById(reviewId = reviewId).toFeedData()
            }
        }
    }
}