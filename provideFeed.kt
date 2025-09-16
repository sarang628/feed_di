package com.sarang.torang.di.feed_di

import com.sarang.torang.compose.feed.FeedItem
import com.sarang.torang.compose.feed.feedType

fun provideFeed(): feedType =
    { feed, onLike, onFavorite, isLogin, onVideoClick, imageHeight, pageScrollAble ->
        FeedItem(
            uiState = feed.toReview().copy(isLogin = isLogin, height = if (imageHeight != 0) imageHeight else 1000),
            onLike = { onLike.invoke(feed.reviewId) },
            onFavorite = { onFavorite.invoke(feed.reviewId) },
            pageScrollAble = pageScrollAble
        )
    }