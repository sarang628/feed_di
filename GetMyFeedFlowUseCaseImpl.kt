package com.sarang.torang.di.feed_di

import android.util.Log
import com.sarang.torang.data.feed.Feed
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.GetMyFeedFlowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

@InstallIn(SingletonComponent::class)
@Module
class GetMyFeedFlowUseCaseImpl {
    @Provides
    fun provideGetMyFeedFlowUseCase(
        feedRepository: FeedRepository,
    ): GetMyFeedFlowUseCase {
        return object : GetMyFeedFlowUseCase {
            override fun invoke(reviewId: Int?): Flow<List<Feed>> {
                Log.d("__provideGetMyFeedFlowUseCase", "load feed : ${reviewId}")
                if(reviewId == null) return MutableStateFlow(listOf())
                return feedRepository.getMyFeed(reviewId = reviewId).map {
                    it.map { it.toFeedData() }
                }
            }
        }
    }
}