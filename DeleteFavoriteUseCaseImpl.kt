package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.DeleteFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DeleteFavoriteUseCaseImpl {
    @Provides
    fun provideDeleteFavoriteUseCase(
        feedRepository: FeedRepository,
    ): DeleteFavoriteUseCase {
        return object : DeleteFavoriteUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.deleteFavorite(reviewId)
            }
        }
    }
}