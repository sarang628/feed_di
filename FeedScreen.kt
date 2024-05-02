package com.sarang.torang.di.feed_di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.sarang.torang.compose.feed.Feed
import com.sarang.torang.compose.feed.Feeds
import com.sarang.torang.compose.feed.MainFeedScreen
import com.sarang.torang.uistate.FeedUiState
import com.sarang.torang.uistate.FeedUiState.Error
import com.sarang.torang.uistate.FeedUiState.Success
import com.sarang.torang.uistate.FeedsUiState

@Composable
fun ProvideFeedScreen(
    onAddReview: (() -> Unit),
    progressTintColor: Color? = null,
) {
    var scrollEnabled by remember { mutableStateOf(true) }
    MainFeedScreen(
        onAddReview = onAddReview,
        feed = {
            Feed(
                review = it.review { },
                isZooming = { scrollEnabled = !it },
                progressTintColor = progressTintColor
            )
        }
    )
}
