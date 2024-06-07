package com.sarang.torang.di.feed_di

import android.util.Log
import com.sarang.torang.data.feed.Feed
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.AddFavoriteUseCase
import com.sarang.torang.usecase.AddLikeUseCase
import com.sarang.torang.usecase.DeleteFavoriteUseCase
import com.sarang.torang.usecase.DeleteLikeUseCase
import com.sarang.torang.usecase.FeedRefreshUseCase
import com.sarang.torang.usecase.GetFeedByRestaurantIdFlowUseCase
import com.sarang.torang.usecase.GetFeedByReviewIdUseCase
import com.sarang.torang.usecase.GetFeedFlowUseCase
import com.sarang.torang.usecase.GetMyFeedFlowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * DomainLayer
 * DataLayer 과 UILayer을 연결
 */
@InstallIn(SingletonComponent::class)
@Module
class FeedServiceModule {
    @Provides
    fun provideFeedRefreshUseCase(
        feedRepository: FeedRepository,
    ): FeedRefreshUseCase {
        return object : FeedRefreshUseCase {
            override suspend fun invoke() {
                feedRepository.loadFeed()
            }
        }
    }

    @Provides
    fun provideAddLikeUseCase(
        feedRepository: FeedRepository,
    ): AddLikeUseCase {
        return object : AddLikeUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.addLike(reviewId)
            }
        }
    }

    @Provides
    fun provideDeleteLikeUseCase(
        feedRepository: FeedRepository,
    ): DeleteLikeUseCase {
        return object : DeleteLikeUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.deleteLike(reviewId)
            }
        }
    }

    @Provides
    fun provideAddFavoriteUseCase(
        feedRepository: FeedRepository,
    ): AddFavoriteUseCase {
        return object : AddFavoriteUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.addFavorite(reviewId)
            }
        }
    }

    @Provides
    fun provideDeleteFavoriteUseCase(
        feedRepository: FeedRepository,
    ): DeleteFavoriteUseCase {
        return object : DeleteFavoriteUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.deleteFavorite(reviewId)
            }
        }
    }

    @Provides
    fun provideGetFeedFlowUseCase(
        feedRepository: FeedRepository,
    ): GetFeedFlowUseCase {
        return object : GetFeedFlowUseCase {
            override suspend fun invoke(): Flow<List<Feed>> {
                return feedRepository.feeds.map {
                    it.map { it.toFeedData() }
                }
            }
        }
    }

    @Provides
    fun provideGetMyFeedFlowUseCase(
        feedRepository: FeedRepository,
    ): GetMyFeedFlowUseCase {
        return object : GetMyFeedFlowUseCase {
            override fun invoke(reviewId: Int): Flow<List<Feed>> {
                return feedRepository.getMyFeed(reviewId = reviewId).map {
                    it.map { it.toFeedData() }
                }
            }
        }
    }

    @Provides
    fun provideGetFeedByRestaurantIdFlowUseCase(
        feedRepository: FeedRepository,
    ): GetFeedByRestaurantIdFlowUseCase {
        return object : GetFeedByRestaurantIdFlowUseCase {
            override fun invoke(restaurantId: Int): Flow<List<Feed>> {
                return feedRepository.getFeedByRestaurantId(restaurantId = restaurantId).map {
                    Log.d("__FeedServiceModule", "get feed by restaurant id result : ${it}")
                    it.map { it.toFeedData() }
                }
            }
        }
    }

    @Provides
    fun provideGetFeedByReviewIdUseCase(
        feedRepository: FeedRepository,
    ): GetFeedByReviewIdUseCase {
        return object : GetFeedByReviewIdUseCase {
            override suspend fun invoke(reviewId: Int): Feed {
                return feedRepository.getFeedByReviewId(reviewId = reviewId).toFeedData()
            }
        }
    }


}