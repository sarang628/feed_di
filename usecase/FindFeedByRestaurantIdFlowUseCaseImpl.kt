package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.usecase.FindFeedByRestaurantIdFlowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class FindFeedByRestaurantIdFlowUseCaseImpl {
    @Provides
    fun provideFindFeedByRestaurantIdFlowUseCase(
        feedLoadRepository: FeedLoadRepository
    ): FindFeedByRestaurantIdFlowUseCase {
        return object : FindFeedByRestaurantIdFlowUseCase {
            override suspend fun invoke(restaurantId: Int) {
                feedLoadRepository.loadByRestaurantId(restaurantId)
            }
        }
    }
}