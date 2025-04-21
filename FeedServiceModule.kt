package com.sarang.torang.di.feed_di

import android.util.Log
import androidx.compose.ui.graphics.Path
import com.sarang.torang.data.dao.LoggedInUserDao
import com.sarang.torang.data.feed.Feed
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.usecase.AddFavoriteUseCase
import com.sarang.torang.usecase.AddLikeUseCase
import com.sarang.torang.usecase.DeleteFavoriteUseCase
import com.sarang.torang.usecase.DeleteLikeUseCase
import com.sarang.torang.usecase.FeedRefreshUseCase
import com.sarang.torang.usecase.FeedWithPageUseCase
import com.sarang.torang.usecase.GetFeedByRestaurantIdFlowUseCase
import com.sarang.torang.usecase.GetFeedByReviewIdUseCase
import com.sarang.torang.usecase.GetFeedFlowUseCase
import com.sarang.torang.usecase.GetMyAllFeedByReviewIdUseCase
import com.sarang.torang.usecase.GetMyFeedFlowUseCase
import com.sarang.torang.usecase.GetUserAllFeedByReviewIdUseCase
import com.sarang.torang.usecase.IsLoginFlowForFeedUseCase
import com.sryang.library.ExpandableText
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

    private val TAG = "__FeedServiceModule"

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
    fun provideFeedWithPageUseCase(
        feedRepository: FeedRepository,
    ): FeedWithPageUseCase {
        return object : FeedWithPageUseCase {
            override suspend fun invoke(page: Int) {
                feedRepository.loadFeedWithPage(page)
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
                    Log.d(TAG, "get feed by restaurant id: $restaurantId, result : $it")
                    if (it.isEmpty()) {
                        throw Exception("해당 식당의 리뷰가 없습니다.")
                    }
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

    @Provides
    fun provideGetUserAllFeedByReviewIdUseCase(
        feedRepository: FeedRepository,
    ): GetUserAllFeedByReviewIdUseCase {
        return object : GetUserAllFeedByReviewIdUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.loadUserAllFeedsByReviewId(reviewId)
            }
        }
    }

    @Provides
    fun provideGetMyAllFeedByReviewIdUseCase(
        feedRepository: FeedRepository,
    ): GetMyAllFeedByReviewIdUseCase {
        return object : GetMyAllFeedByReviewIdUseCase {
            override suspend fun invoke(reviewId: Int) {
                feedRepository.loadMyAllFeedsByReviewId(reviewId)
            }
        }
    }

    @Provides
    fun isLoginFlowUseCase(
        loggedInUserDao: LoggedInUserDao,
    ): IsLoginFlowForFeedUseCase {
        return object : IsLoginFlowForFeedUseCase {
            override val isLogin: Flow<Boolean>
                get() = loggedInUserDao.getLoggedInUser().map { it != null }
        }
    }


}