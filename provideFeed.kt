package com.sarang.torang.di.feed_di

import com.sarang.torang.compose.feed.FeedItem
import com.sarang.torang.compose.feed.type.feedType
import com.sarang.torang.data.basefeed.FeedItemClickEvents

fun provideFeed(): feedType = {
        FeedItem(
            uiState = it.feed.toReview(it.isLogin),
            feedItemClickEvents = FeedItemClickEvents(
                onLike = { it.onLike.invoke(it.feed.reviewId) },
                onFavorite = { it.onFavorite.invoke(it.feed.reviewId) },
            ),
            pageScroll = it.pageScrollable,
            onPage = {}
        )
    }