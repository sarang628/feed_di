package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.data.feed.Feed
import com.sarang.torang.di.feed_di.toFeedData
import com.sarang.torang.repository.feed.FeedFlowRepository
import com.sarang.torang.usecase.GetFeedByReviewIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

@InstallIn(SingletonComponent::class)
@Module
class GetFeedByReviewIdUseCaseImpl {
    @Provides
    fun provideGetFeedByReviewIdUseCase(
        feedFlowRepository: FeedFlowRepository,
    ): GetFeedByReviewIdUseCase {
        return object : GetFeedByReviewIdUseCase {
            override fun invoke(reviewId: Int?): Flow<Feed?> {
                if(reviewId == null) return MutableStateFlow(null)
                return feedFlowRepository.findByIdFlow(reviewId = reviewId).map { if(it == null) null else it.toFeedData() }
            }
        }
    }
}