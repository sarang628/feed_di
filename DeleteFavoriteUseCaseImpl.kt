package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FavoriteRepository
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
        favoriteRepository: FavoriteRepository,
    ): DeleteFavoriteUseCase {
        return object : DeleteFavoriteUseCase {
            override suspend fun invoke(reviewId: Int) {
                favoriteRepository.deleteFavorite(reviewId)
            }
        }
    }
}