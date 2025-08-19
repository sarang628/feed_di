package com.sarang.torang.di.feed_di

import com.sarang.torang.data.feed.Feed
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
                if(reviewId == null) return Feed.Empty
                return feedRepository.getFeedByReviewId(reviewId = reviewId).toFeedData()
            }
        }
    }
}