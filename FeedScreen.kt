package com.sryang.torang.di.feed_di

import androidx.compose.runtime.Composable
import com.sryang.torang.compose.feed.FeedScreen
import com.sryang.torang.compose.feed.Feeds

@Composable
fun ProvideFeedScreen(
    clickAddReview: (() -> Unit),
    onProfile: ((Int) -> Unit),
    onImage: ((Int) -> Unit),
    onName: ((Int) -> Unit),
    onRestaurant: ((Int) -> Unit),
    onComment: ((Int) -> Unit),
    onShare: ((Int) -> Unit),
    onMenu: ((Int) -> Unit),
    ratingBar: @Composable (Float) -> Unit
) {
    FeedScreen(
        clickAddReview = clickAddReview,
    ) { list, onLike, onFavorite, onRefresh, onBottom, isRefreshing, isEmpty, isLoading ->
        Feeds(
            list = ArrayList(list.map { it.review() }),
            onLike = onLike,
            onFavorite = onFavorite,
            onRefresh = onRefresh,
            onBottom = onBottom,
            onProfile = onProfile,
            onMenu = onMenu,
            onImage = onImage,
            onName = onName,
            onComment = onComment,
            onShare = onShare,
            onRestaurant = onRestaurant,
            ratingBar = ratingBar,
            isRefreshing = isRefreshing,
            isEmpty = isEmpty,
            isLoading = isLoading
        )
    }
}
