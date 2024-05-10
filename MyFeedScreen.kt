package com.sarang.torang.di.feed_di

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.sarang.torang.compose.feed.Feed
import com.sarang.torang.compose.feed.MyFeedScreen
import com.sarang.torang.di.image.provideTorangAsyncImage

fun provideMyFeedScreen(
    navController: NavHostController,
    reviewId: Int,
    progressTintColor: Color? = null,
    onImage: ((Int) -> Unit)? = null,
    onShowComment: () -> Unit,
    onRestaurant: (Int) -> Unit,
    onProfile: ((Int) -> Unit)? = null,
    onBack: (() -> Unit)? = null,
): @Composable (onComment: ((Int) -> Unit), onMenu: ((Int) -> Unit), onShare: ((Int) -> Unit)) -> Unit =
    { onComment, onMenu, onShare ->
        val listState = rememberLazyListState()
        var scrollEnabled by remember { mutableStateOf(true) }

        MyFeedScreen(
            reviewId = reviewId,
            onBack = onBack,
            listState = listState,
            feed = {
                Feed(
                    review = it.review(onComment = {
                        onComment.invoke(it.reviewId)
                        onShowComment.invoke()
                    },
                        onShare = { onShare.invoke(it.reviewId) },
                        onMenu = { onMenu.invoke(it.reviewId) },
                        onName = { onProfile?.invoke(it.userId) },
                        onRestaurant = {onRestaurant.invoke(it.restaurantId)},
                        onImage = onImage,
                        onProfile = { onProfile?.invoke(it.userId) }),
                    isZooming = { scrollEnabled = !it },
                    progressTintColor = progressTintColor,
                    image = provideTorangAsyncImage()
                )
            }
        )
    }
