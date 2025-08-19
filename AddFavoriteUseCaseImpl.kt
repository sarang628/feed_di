package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.AddFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AddFavoriteUseCaseImpl {
    @Provides
    fun provideAddFavoriteUseCase(
        feedRepository: FeedRepository,
    ): AddFavoriteUseCase {
        return object : AddFavoriteUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.addFavorite(reviewId)
            }
        }
    }
}