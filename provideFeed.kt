package com.sarang.torang.di.feed_di

import androidx.compose.runtime.CompositionLocalProvider
import com.sarang.torang.compose.feed.FeedItem
import com.sarang.torang.compose.feed.internal.components.type.LocalVideoPlayerType
import com.sarang.torang.compose.feed.type.feedType
import com.sarang.torang.data.basefeed.FeedItemClickEvents
import com.sarang.torang.di.basefeed_di.CustomVideoPlayerType

fun provideFeed(): feedType = {
    CompositionLocalProvider(LocalVideoPlayerType provides CustomVideoPlayerType) {
        FeedItem(
            uiState = it.feed.toReview(it.isLogin),
            feedItemClickEvents = FeedItemClickEvents(
                onLike = { it.onLike.invoke(it.feed.reviewId) },
                onFavorite = { it.onFavorite.invoke(it.feed.reviewId) },
            ),
            pageScroll = it.pageScrollable,
            onPage = {},
            isPlaying = it.isPlaying
        )
    }
}