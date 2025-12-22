package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.uistate.FeedLoadingUiState
import com.sarang.torang.usecase.GetFeedLodingFlowUseCase
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
        feedLoadRepository: FeedLoadRepository,
    ): GetFeedLodingFlowUseCase {
        return object : GetFeedLodingFlowUseCase {
            override fun invoke(coroutineScope: CoroutineScope): StateFlow<FeedLoadingUiState> {
                return feedLoadRepository.feeds.map {
                    feeds ->
                    if (feeds == null) FeedLoadingUiState.Loading
                    else FeedLoadingUiState.Success
                }.stateIn(
                    scope = coroutineScope,
                    started = SharingStarted.Companion.WhileSubscribed(5_000),
                    initialValue = FeedLoadingUiState.Loading
                )
            }
        }
    }
}