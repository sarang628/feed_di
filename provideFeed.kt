package com.sarang.torang.di.feed_di

import com.sarang.torang.compose.feed.FeedItem
import com.sarang.torang.compose.feed.FeedItemClickEvents
import com.sarang.torang.compose.feed.type.feedType

fun provideFeed(): feedType = {
        FeedItem(
            uiState = it.feed.toReview(it.isLogin),
            feedItemClickEvents = FeedItemClickEvents(
                onLike = { it.onLike.invoke(it.feed.reviewId) },
                onFavorite = { it.onFavorite.invoke(it.feed.reviewId) },
            ),
            pageScrollAble = it.pageScrollable,
            onPage = {}
        )
    }