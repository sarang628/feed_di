package com.sarang.torang.di.feed_di.usecase

import android.util.Log
import com.sarang.torang.repository.LikeRepository
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
        likeRepository: LikeRepository,
    ): DeleteLikeUseCase {
        return object : DeleteLikeUseCase {
            override suspend fun invoke(reviewId: Int) {
                Log.i("__provideDeleteLikeUseCase", "deleteLike : reviewId $reviewId")
                likeRepository.deleteLike(reviewId)
            }
        }
    }
}