package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.usecase.FindFeedByPictureIdFlowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class FindFeedByPictureIdFlowUseCaseImpl {
    @Provides
    fun provideFindFeedByPictureIdFlowUseCase(
        feedLoadRepository: FeedLoadRepository
    ): FindFeedByPictureIdFlowUseCase {
        return object : FindFeedByPictureIdFlowUseCase {
            override suspend fun invoke(restaurantId: Int) {
                feedLoadRepository.loadByRestaurantId(restaurantId)
            }
        }
    }
}