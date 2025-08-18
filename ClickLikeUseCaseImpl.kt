package com.sarang.torang.di.feed_di

import com.sarang.torang.data.dao.LikeDao
import com.sarang.torang.usecase.AddLikeUseCase
import com.sarang.torang.usecase.ClickLikeUseCase
import com.sarang.torang.usecase.DeleteLikeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ClickLikeUseCaseImpl {

    @Provides
    fun provideClickLikeUseCase(
        likeDao: LikeDao,
        addLikeUseCase: AddLikeUseCase,
        deleteLikeUseCase: DeleteLikeUseCase
    ): ClickLikeUseCase {
        return object : ClickLikeUseCase {
            override suspend fun invoke(reviewId: Int) {
                try {
                    likeDao.getLike1(reviewId)
                    deleteLikeUseCase.invoke(reviewId)
                } catch (e: Exception) {
                    addLikeUseCase.invoke(reviewId)
                }
            }
        }
    }
}