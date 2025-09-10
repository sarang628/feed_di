package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.FeedRefreshUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class FeedRefreshUseCaseImpl {
    @Provides
    fun provideFeedRefreshUseCase(
        feedRepository: FeedRepository,
    ): FeedRefreshUseCase {
        return object : FeedRefreshUseCase {
            override suspend fun invoke() {
                feedRepository.findAll()
            }
        }
    }
}