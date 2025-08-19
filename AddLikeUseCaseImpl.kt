package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.AddLikeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AddLikeUseCaseImpl {
    @Provides
    fun provideAddLikeUseCase(
        feedRepository: FeedRepository,
    ): AddLikeUseCase {
        return object : AddLikeUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.addLike(reviewId)
            }
        }
    }
}