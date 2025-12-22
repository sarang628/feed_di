package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.di.feed_di.toFeedData
import com.sarang.torang.repository.feed.FeedFlowRepository
import com.sarang.torang.uistate.FeedUiState
import com.sarang.torang.usecase.GetFeedByRestaurantIdFlowUseCase
import com.sarang.torang.usecase.IsLoginFlowForFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

@InstallIn(SingletonComponent::class)
@Module
class GetFeedByRestaurantIdFlowUseCaseImpl {
    val tag = "__GetFeedByRestaurantIdFlowUseCaseImpl"

    @Provides
    fun provideGetFeedByRestaurantIdFlowUseCase(
        feedFlowRepository: FeedFlowRepository,
        loginFlowForFeedUseCase: IsLoginFlowForFeedUseCase
    ): GetFeedByRestaurantIdFlowUseCase {
        return object : GetFeedByRestaurantIdFlowUseCase {
            override fun invoke(restaurantId: Int?): Flow<FeedUiState> {
                if(restaurantId == null) return MutableStateFlow(FeedUiState())
                return combine(
                    feedFlowRepository.findRestaurantFeedsFlow(restaurantId = restaurantId),
                    loginFlowForFeedUseCase.isLogin
                )
                { feed, isLogin ->
                    FeedUiState(
                        list = feed.map { it.toFeedData() },
                        isLogin = isLogin
                    )
                }
            }
        }
    }
}