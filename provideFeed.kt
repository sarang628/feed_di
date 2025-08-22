package com.sarang.torang.di.feed_di

import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.FeedItem
import com.sarang.torang.compose.feed.feedType

fun provideFeed(): feedType =
    { feed, onLike, onFavorite, isLogin, onVideoClick, imageHeight, pageScrollAble ->
        FeedItem(
            uiState = feed.toReview(),
            onLike = { onLike.invoke(feed.reviewId) },
            onFavorite = { onFavorite.invoke(feed.reviewId) },
            isLogin = isLogin,
            imageHeight = if (imageHeight != 0) imageHeight.dp else 400.dp,
            pageScrollAble = pageScrollAble
        )
    }