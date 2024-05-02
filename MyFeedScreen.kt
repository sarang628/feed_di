package com.sarang.torang.di.feed_di

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.sarang.torang.compose.feed.Feed
import com.sarang.torang.compose.feed.MyFeedScreen

@Composable
fun ProvideMyFeedScreen(
    reviewId: Int,
    onBack: (() -> Unit)? = null,
    progressTintColor: Color? = null,
    onComment: ((Int) -> Unit)? = null,
    onShare: ((Int) -> Unit)? = null,
    onMenu: ((Int) -> Unit)? = null,
    onName: ((Int) -> Unit)? = null,
    onRestaurant: ((Int) -> Unit)? = null,
    onImage: ((Int) -> Unit)? = null,
    onProfile: ((Int) -> Unit)? = null,
) {
    val listState = rememberLazyListState()
    var scrollEnabled by remember { mutableStateOf(true) }

    MyFeedScreen(
        reviewId = reviewId,
        onBack = onBack,
        listState = listState,
        feed = {
            Feed(
                review = it.review(onComment = { onComment?.invoke(it.reviewId) },
                    onShare = { onShare?.invoke(it.reviewId) },
                    onMenu = { onMenu?.invoke(it.reviewId) },
                    onName = { onName?.invoke(it.userId) },
                    onRestaurant = { onRestaurant?.invoke(it.restaurantId) },
                    onImage = onImage,
                    onProfile = { onProfile?.invoke(it.userId) }),
                isZooming = { scrollEnabled = !it },
                progressTintColor = progressTintColor
            )
        }
    )
}
