package com.sarang.torang.di.feed_di

import android.util.Log
import com.sarang.torang.data.feed.Feed
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.GetFeedByRestaurantIdFlowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

@InstallIn(SingletonComponent::class)
@Module
class GetFeedByRestaurantIdFlowUseCaseImpl {
    val tag = "__GetFeedByRestaurantIdFlowUseCaseImpl"

    @Provides
    fun provideGetFeedByRestaurantIdFlowUseCase(
        feedRepository: FeedRepository,
    ): GetFeedByRestaurantIdFlowUseCase {
        return object : GetFeedByRestaurantIdFlowUseCase {
            override fun invoke(restaurantId: Int?): Flow<List<Feed>> {
                if(restaurantId == null) return MutableStateFlow(listOf())
                return feedRepository.getFeedByRestaurantId(restaurantId = restaurantId).map {
                    Log.d(tag, "get feed by restaurant id: $restaurantId, result : $it")
                    if (it.isEmpty()) {
                        //throw Exception("해당 식당의 리뷰가 없습니다.") //TODO: 앱 죽음.
                    }
                    it.map { it.toFeedData() }
                }
            }
        }
    }
}