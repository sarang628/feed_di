package com.sarang.torang.di.feed_di

import android.util.Log
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.uistate.FeedLoadingUiState
import com.sarang.torang.uistate.FeedUiState
import com.sarang.torang.usecase.GetFeedFlowUseCase
import com.sarang.torang.usecase.GetFeedLodingFlowUseCase
import com.sarang.torang.usecase.IsLoginFlowForFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@InstallIn(SingletonComponent::class)
@Module
class GetFeedFlowUseCaseImpl {
    @Provides
    fun provideGetFeedFlowUseCase(
        feedRepository: FeedRepository,
        loginFlowForFeedUseCase: IsLoginFlowForFeedUseCase
    ): GetFeedFlowUseCase {
        return object : GetFeedFlowUseCase {
            override fun invoke(coroutineScope: CoroutineScope): StateFlow<FeedUiState> {
                return combine(feedRepository.feeds, loginFlowForFeedUseCase.isLogin){ feeds , isLogin ->
                    FeedUiState(feeds?.map { it.toFeedData() } ?: listOf(), isLogin)
                }.stateIn(
                    scope = coroutineScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = FeedUiState()
                )
            }
        }
    }
}