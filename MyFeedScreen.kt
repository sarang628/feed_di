package com.sarang.torang.di.feed_di

import android.util.Log
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import com.sarang.torang.compose.feed.Feeds
import com.sarang.torang.compose.feed.MyFeedScreen
import com.sarang.torang.uistate.FeedUiState
import com.sarang.torang.uistate.FeedUiState.Error
import com.sarang.torang.uistate.FeedUiState.Success
import com.sarang.torang.uistate.FeedsUiState

@Composable
fun ProvideMyFeedScreen(
    onAddReview: (() -> Unit),
    reviewId: Int,
    onBack: (() -> Unit)? = null
) {
    val TAG = "_ProvideFeedScreen"

    val listState = rememberLazyListState()

    MyFeedScreen(
        onAddReview = onAddReview,
        reviewId = reviewId,
        onBack = onBack,
        listState = listState,
        feeds = { feedUiState, onRefresh, onBottom, isRefreshing ->
            when (feedUiState) {
                is FeedUiState.Loading -> {
                    Feeds(
                        onRefresh = onRefresh,
                        onBottom = onBottom,
                        isRefreshing = isRefreshing,
                        feedsUiState = FeedsUiState.Loading
                    )
                }

                is Success -> {
                    Feeds(
                        listState = listState,
                        onRefresh = onRefresh,
                        onBottom = onBottom,
                        isRefreshing = isRefreshing,
                        feedsUiState = FeedsUiState.Success(
                            feedUiState.list.map {
                                it.review(
                                    onProfile = { Log.d(TAG, "onProfile()") },
                                    onImage = { Log.d(TAG, "onImage()") },
                                    onRestaurant = { Log.d(TAG, "onRestaurant()") },
                                    onName = { Log.d(TAG, "onName()") },
                                    onMenu = { Log.d(TAG, "onMenu()") },
                                    onShare = { Log.d(TAG, "onShare()") },
                                    onComment = { Log.d(TAG, "onComment()") }
                                )
                            }
                        )
                    )
                }

                is Error -> {

                }

                else -> {}
            }
        }
    )
}
