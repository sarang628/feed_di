package com.sarang.torang.di.feed_di

import android.util.Log
import com.sarang.torang.core.database.dao.LikeDao
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
                    val count = likeDao.hasLike(reviewId)
                    if (count == 0) {
                        addLikeUseCase.invoke(reviewId)
                    } else {
                        deleteLikeUseCase.invoke(reviewId)
                    }
                } catch (e: Exception) {
                    Log.i("__provideClickLikeUseCase", "click like failed reviewId : ${reviewId}. cause: ${e.toString()}")
                }
            }
        }
    }
}