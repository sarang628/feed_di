package com.sarang.torang.di.feed_di

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sarang.torang.RootNavController
import com.sarang.torang.compose.feed.FeedItem
import com.sarang.torang.compose.feed.type.feedType
import com.sarang.torang.data.basefeed.FeedItemClickEvents
import com.sarang.torang.di.basefeed_di.CustomExpandableTextType
import com.sarang.torang.di.basefeed_di.CustomFeedImageLoader
import com.sarang.torang.di.basefeed_di.CustomVideoPlayerType

private const val tag = "__provideFeed"
fun provideFeed(
    rootNavController : RootNavController = RootNavController(),
    onComment    : (Int) -> Unit = { Log.w(tag, "onComment callback is not set") },
    onShare      : (Int) -> Unit = { Log.w(tag, "onShare callback is not set") },
    onMenu       : (Int) -> Unit = { Log.w(tag, "onMenu callback is not set") },
): feedType = { data ->
    var lastPage : Int by remember { mutableStateOf(0) }
    var isPlayed by remember { mutableStateOf(false) }
    FeedItem(
        videoLoader         = CustomVideoPlayerType(onPlayed = { isPlayed = true }),
        imageLoader         = { CustomFeedImageLoader().invoke(it) },
        expandableText      = CustomExpandableTextType,
        uiState             = data.feed.toReview(data.isLogin),
        pageScroll          = data.pageScrollable,
        onPage              = { lastPage = it.page},
        isPlaying           = data.isPlaying || isPlayed,
        feedItemClickEvents = FeedItemClickEvents(onLike       = { data.onLike.invoke(data.feed.reviewId) },
                                                  onFavorite   = { data.onFavorite.invoke(data.feed.reviewId) },
                                                  onComment    = { onComment(data.feed.reviewId) },
                                                  onShare      = { onShare(data.feed.reviewId) },
                                                  onMenu       = { onMenu(data.feed.reviewId) },
                                                  onLikes      = { rootNavController.like(data.feed.reviewId) },
                                                  onImage      = { rootNavController.imagePager(data.feed.reviewId, lastPage) },
                                                  onName       = { rootNavController.profile(data.feed.userId) },
                                                  onProfile    = { rootNavController.profile(data.feed.userId) },
                                                  onRestaurant = { rootNavController.restaurant(data.feed.restaurantId) })
    )
}