package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.FindFeedByPictureIdFlowUseCase
import com.sarang.torang.usecase.FindFeedByRestaurantIdFlowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class FindFeedByPictureIdFlowUseCaseImpl {
    @Provides
    fun provideFindFeedByPictureIdFlowUseCase(
        feedRepository: FeedRepository
    ): FindFeedByPictureIdFlowUseCase {
        return object : FindFeedByPictureIdFlowUseCase {
            override suspend fun invoke(restaurantId: Int) {
                feedRepository.findByRestaurantId(restaurantId)
            }
        }
    }
}