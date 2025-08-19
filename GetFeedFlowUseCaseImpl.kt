package com.sarang.torang.di.feed_di

import com.sarang.torang.data.feed.Feed
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.GetFeedFlowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@InstallIn(SingletonComponent::class)
@Module
class GetFeedFlowUseCaseImpl {
    @Provides
    fun provideGetFeedFlowUseCase(
        feedRepository: FeedRepository,
    ): GetFeedFlowUseCase {
        return object : GetFeedFlowUseCase {
            override fun invoke(): Flow<List<Feed>> {
                return feedRepository.feeds.map {
                    it.map { it.toFeedData() }
                }
            }
        }
    }
}