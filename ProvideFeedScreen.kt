package com.sarang.torang.di.feed_di

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sarang.torang.BuildConfig

@Composable
fun ProvideFeedScreen(navController: NavHostController) {
    FeedScreen(
        onProfile = { navController.navigate("profile/$it") },
        onRestaurant = { navController.navigate("restaurant/$it") },
        onImage = {},
        onName = {},
        clickAddReview = { navController.navigate("addReview") },
        profileImageServerUrl = BuildConfig.PROFILE_IMAGE_SERVER_URL,
        imageServerUrl = BuildConfig.REVIEW_IMAGE_SERVER_URL,
        ratingBar = {}
    )
}