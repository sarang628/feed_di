package com.sarang.torang.di.feed_di.usecase

import android.util.Log
import com.sarang.torang.core.database.dao.LikeDao
import com.sarang.torang.repository.LikeRepository
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
                if (!likeDao.has(reviewId)) {
                    addLikeUseCase.invoke(reviewId)
                } else {
                    deleteLikeUseCase.invoke(reviewId)
                }
            }
        }
    }
}