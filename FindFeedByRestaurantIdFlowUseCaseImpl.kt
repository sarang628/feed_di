package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FeedRepository
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
        feedRepository: FeedRepository
    ): FindFeedByRestaurantIdFlowUseCase {
        return object : FindFeedByRestaurantIdFlowUseCase {
            override suspend fun invoke(restaurantId: Int) {
                feedRepository.findByRestaurantId(restaurantId)
            }
        }
    }
}