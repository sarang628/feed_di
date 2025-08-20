package com.sarang.torang.di.feed_di

import androidx.compose.ui.unit.dp
import com.sarang.torang.compose.feed.Feed
import com.sarang.torang.compose.feed.feedType
import com.sarang.torang.di.image.provideZoomableTorangAsyncImage
import com.sarang.torang.provideExpandableText

fun provideFeed(): feedType =
    { feed, onLike, onFavorite, isLogin, onVideoClick, imageHeight, pageScrollAble ->
        Feed(
            review = feed.toReview(),
            imageLoadCompose = provideZoomableTorangAsyncImage(),
            onLike = { onLike.invoke(feed.reviewId) },
            onFavorite = { onFavorite.invoke(feed.reviewId) },
            isLogin = isLogin,
            expandableText = provideExpandableText(),
            videoPlayer = { VideoPlayerScreen(videoUrl = it, isPlaying = feed.isPlaying, onClick = onVideoClick, onPlay = {}) },
            imageHeight = if (imageHeight != 0) imageHeight.dp else 400.dp,
            pageScrollAble = pageScrollAble
        )
    }