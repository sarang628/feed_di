package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.data.feed.Feed
import com.sarang.torang.di.feed_di.toFeedData
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.LoadFeedByReviewIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

@InstallIn(SingletonComponent::class)
@Module
class LoadFeedByReviewIdUseCaseImpl {
    @Provides
    fun provideLoadFeedByReviewIdUseCase(
        feedRepository: FeedRepository,
    ): LoadFeedByReviewIdUseCase {
        return object : LoadFeedByReviewIdUseCase {
            override suspend fun invoke(reviewId: Int) {
                return feedRepository.loadById(reviewId = reviewId)
            }
        }
    }
}