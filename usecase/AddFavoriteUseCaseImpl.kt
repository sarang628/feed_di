package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.repository.FavoriteRepository
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
        favoriteRepository: FavoriteRepository,
    ): AddFavoriteUseCase {
        return object : AddFavoriteUseCase {
            override suspend fun invoke(reviewId: Int) {
                favoriteRepository.addFavorite(reviewId)
            }
        }
    }
}