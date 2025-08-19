package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.DeleteLikeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DeleteLikeUseCaseImpl {
    @Provides
    fun provideDeleteLikeUseCase(
        feedRepository: FeedRepository,
    ): DeleteLikeUseCase {
        return object : DeleteLikeUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.deleteLike(reviewId)
            }
        }
    }
}