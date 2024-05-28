package com.sarang.torang.di.feed_di

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sarang.torang.compose.feed.MyFeedScreen
import com.sarang.torang.data.feed.Feed

fun provideMyFeedScreen(
    reviewId: Int,
    onBack: (() -> Unit)? = null,
    feed: @Composable ((
        feed: Feed,
        onLike: (Int) -> Unit,
        onFavorite: (Int) -> Unit,
    ) -> Unit),
): @Composable (onComment: ((Int) -> Unit), onMenu: ((Int) -> Unit), onShare: ((Int) -> Unit)) -> Unit =
    { onComment, onMenu, onShare ->
        val listState = rememberLazyListState()
        var scrollEnabled by remember { mutableStateOf(true) }

        MyFeedScreen(
            reviewId = reviewId,
            onBack = onBack,
            listState = listState,
            feed = feed
        )
    }
