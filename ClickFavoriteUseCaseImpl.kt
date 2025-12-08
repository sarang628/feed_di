package com.sarang.torang.di.feed_di

import com.sarang.torang.core.database.dao.FavoriteDao
import com.sarang.torang.usecase.AddFavoriteUseCase
import com.sarang.torang.usecase.ClickFavorityUseCase
import com.sarang.torang.usecase.DeleteFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ClickFavoriteUseCaseImpl {

    @Provides
    fun provideClickFavoriteUseCase(
        favoriteDao: FavoriteDao,
        addFavoriteUseCase: AddFavoriteUseCase,
        deleteFavoriteUseCase: DeleteFavoriteUseCase
    ): ClickFavorityUseCase {
        return object : ClickFavorityUseCase {
            override suspend fun invoke(reviewId: Int) {
                try {
                    favoriteDao.findByReviewId(reviewId)
                    deleteFavoriteUseCase.invoke(reviewId)
                } catch (e: Exception) {
                    addFavoriteUseCase.invoke(reviewId)
                }
            }
        }
    }
}