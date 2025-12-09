package com.sarang.torang.di.feed_di.usecase

import android.util.Log
import com.sarang.torang.repository.LikeRepository
import com.sarang.torang.usecase.AddLikeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AddLikeUseCaseImpl {
    @Provides
    fun provideAddLikeUseCase(
        likeRepository: LikeRepository,
    ): AddLikeUseCase {
        return object : AddLikeUseCase {
            override suspend fun invoke(reviewId: Int) {
                Log.i("__provideAddLikeUseCase", "addLike : reviewId $reviewId")
                likeRepository.addLike(reviewId)
            }
        }
    }
}