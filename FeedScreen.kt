package com.sarang.torang.di.feed_di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.sarang.torang.compose.feed.Feed
import com.sarang.torang.compose.feed.MainFeedScreen

fun provideFeedScreen(
    navController: NavHostController,
    progressTintColor: Color? = null,
    onImage: ((Int) -> Unit)? = null,
    onShowComment: () -> Unit,
): @Composable (onComment: ((Int) -> Unit), onMenu: ((Int) -> Unit), onShare: ((Int) -> Unit)) -> Unit =
    { onComment, onMenu, onShare ->
        var scrollEnabled by remember { mutableStateOf(true) }
        MainFeedScreen(
            onAddReview = { navController.navigate("addReview") },
            feed = {
                Feed(
                    review = it.review(
                        onComment = {
                            onComment.invoke(it.reviewId)
                            onShowComment.invoke()
                        },
                        onShare = { onShare.invoke(it.reviewId) },
                        onMenu = { onMenu.invoke(it.reviewId) },
                        onName = { navController.navigate("profile/${it.userId}") },
                        onRestaurant = { navController.navigate("restaurant/${it.restaurantId}") },
                        onImage = onImage,
                        onProfile = { navController.navigate("profile/${it.userId}") }
                    ),
                    isZooming = { scrollEnabled = !it },
                    progressTintColor = progressTintColor
                )
            }
        )
    }
