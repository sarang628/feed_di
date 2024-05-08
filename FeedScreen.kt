package com.sarang.torang.di.feed_di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sarang.torang.compose.feed.Feed
import com.sarang.torang.compose.feed.Feeds
import com.sarang.torang.compose.feed.MainFeedScreen
import com.sarang.torang.uistate.FeedUiState
import com.sarang.torang.uistate.FeedUiState.Error
import com.sarang.torang.uistate.FeedUiState.Success
import com.sarang.torang.uistate.FeedsUiState

@Composable
fun ProvideFeedScreen(
    navController: NavHostController,
    progressTintColor: Color? = null,
    onImage: ((Int) -> Unit)? = null,
    onAddReview: () -> Unit,
    onShowComment: () -> Unit,
    onTop: Boolean,
    consumeOnTop: () -> Unit
): @Composable (onComment: ((Int) -> Unit), onMenu: ((Int) -> Unit), onShare: ((Int) -> Unit)) -> Unit =
    { onComment, onMenu, onShare ->
        var scrollEnabled by remember { mutableStateOf(true) }
        val mainNavHostController = rememberNavController()

        NavHost(navController = mainNavHostController, startDestination = "mainFeed") {
            composable("mainFeed") {
                MainFeedScreen(
                    onAddReview = onAddReview,
                    onTop = onTop,
                    consumeOnTop = consumeOnTop,
                    feed = {
                        Feed(
                            review = it.review(
                                onComment = {
                                    onComment.invoke(it.reviewId)
                                    onShowComment.invoke()
                                },
                                onShare = { onShare.invoke(it.reviewId) },
                                onMenu = { onMenu.invoke(it.reviewId) },
                                onName = { mainNavHostController.navigate("profile/${it.userId}") },
                                onRestaurant = { navController.navigate("restaurant/${it.restaurantId}") },
                                onImage = onImage,
                                onProfile = { mainNavHostController.navigate("profile/${it.userId}") }
                            ),
                            isZooming = { scrollEnabled = !it },
                            progressTintColor = progressTintColor
                        )
                    }
                )
            }
            composable("profile/{id}") {
                val userId = it.arguments?.getString("id")?.toInt()
                ProfileScreenNavHost(
                    id = userId,
                    onClose = { mainNavHostController.popBackStack() },
                    onEmailLogin = { navController.navigate("emailLogin") },
                    onReview = { mainNavHostController.navigate("myFeed/${it}") },
                    myFeed = {
                        ProvideMyFeedScreen(/*ProfileScreenNavHost*/
                            navController = navController,
                            reviewId = it.arguments?.getString("reviewId")?.toInt() ?: 0,
                            onEdit = { navController.navigate("modReview/${it}") },
                            onProfile = { mainNavHostController.navigate("profile/${it}") },
                            onBack = { mainNavHostController.popBackStack() }
                        )
                    })
            }
            composable("myFeed/{reviewId}") {
                ProvideMyFeedScreen(/*provideFeedScreen*/
                    navController = navController,
                    reviewId = it.arguments?.getString("reviewId")?.toInt() ?: 0,
                    onEdit = { navController.navigate("modReview/${it}") },
                    onProfile = { mainNavHostController.navigate("profile/${it}") },
                    onBack = { mainNavHostController.popBackStack() }
                )
            }
        }
    }
