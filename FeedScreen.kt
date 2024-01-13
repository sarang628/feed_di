package com.sryang.torang.di.feed_di

import androidx.compose.runtime.Composable
import com.sryang.torang.compose.feed.FeedScreen
import com.sryang.torang.compose.feed.Feeds
import com.sryang.torang.uistate.FeedsUiState

@Composable
fun ProvideFeedScreen(
    onAddReview: (() -> Unit),
    ratingBar: @Composable (Float) -> Unit
) {
    FeedScreen(
        onAddReview = onAddReview,
    ) { list,  onRefresh, onBottom, isRefreshing ->
        Feeds(
            onRefresh = onRefresh,
            onBottom = onBottom,
            ratingBar = { _, _ -> ratingBar },
            isRefreshing = isRefreshing,
            feedsUiState = FeedsUiState.Success(
                list.map { it.review() }
            )
        )
    }
}
