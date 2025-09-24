package com.sarang.torang.di.feed_di

import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.uistate.FeedLoadingUiState
import com.sarang.torang.usecase.GetFeedLodingFlowUseCase
import com.sarang.torang.usecase.IsLoginFlowForFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@InstallIn(SingletonComponent::class)
@Module
class GetFeedLoadingFlowUseCaseImpl {
    @Provides
    fun provideGetFeedFlowUseCase(
        feedRepository: FeedRepository,
    ): GetFeedLodingFlowUseCase {
        return object : GetFeedLodingFlowUseCase {
            override fun invoke(coroutineScope: CoroutineScope): StateFlow<FeedLoadingUiState> {
                return feedRepository.feeds.map {
                    feeds ->
                    if (feeds == null) FeedLoadingUiState.Loading
                    else FeedLoadingUiState.Success
                }.stateIn(
                    scope = coroutineScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = FeedLoadingUiState.Loading
                )
            }
        }
    }
}