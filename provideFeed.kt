package com.sarang.torang.di.feed_di

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sarang.torang.compose.feed.FeedItem
import com.sarang.torang.compose.feed.type.feedType
import com.sarang.torang.data.basefeed.FeedItemClickEvents
import com.sarang.torang.di.basefeed_di.CustomExpandableTextType
import com.sarang.torang.di.basefeed_di.CustomFeedImageLoader
import com.sarang.torang.di.basefeed_di.CustomVideoPlayerType

fun provideFeed(): feedType = {
    var isPlayed by remember { mutableStateOf(false) }
    FeedItem(
        videoLoader         = CustomVideoPlayerType(onPlayed = { isPlayed = true }),
        imageLoader         = { CustomFeedImageLoader().invoke(it) },
        expandableText      = CustomExpandableTextType,
        uiState             = it.feed.toReview(it.isLogin),
        pageScroll          = it.pageScrollable,
        onPage              = {},
        isPlaying           = it.isPlaying || isPlayed,
        feedItemClickEvents = FeedItemClickEvents(  onLike      = { it.onLike.invoke(it.feed.reviewId) },
                                                    onFavorite  = { it.onFavorite.invoke(it.feed.reviewId) } )
    )
}