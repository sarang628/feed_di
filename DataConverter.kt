package com.sarang.torang.di.feed_di

import android.util.Log
import com.google.gson.GsonBuilder
import com.sarang.torang.BuildConfig
import com.sarang.torang.compose.feed.internal.components.FeedBottomUiState
import com.sarang.torang.compose.feed.internal.components.FeedTopUiState
import com.sarang.torang.data.ReviewAndImage
import com.sarang.torang.data.basefeed.FeedItemUiState
import com.sarang.torang.data.feed.Feed
import com.sarang.torang.data.feed.FeedImage

private const val TAG = "__DataConverter"
fun Feed.toReview(isLogin : Boolean = false): FeedItemUiState {
    return FeedItemUiState(
        reviewImages = this.reviewImages.map { it.url },
        feedTopUiState = FeedTopUiState(
            userName = this.name,
            profilePictureUrl = this.profilePictureUrl,
            restaurantName = this.restaurantName,
            rating = this.rating
        ),
        feedBottomUiState = FeedBottomUiState(
            likeAmount = this.likeAmount,
            isLike = this.isLike,
            isFavorite = this.isFavorite,
            isLogin = isLogin
        ),
        commentAmount = this.commentAmount,
        comments = listOf(),
        contents = this.contents,
        createDate = createDate,
        height = if(this.reviewImages.isEmpty()) 500 else this.reviewImages[0].height,
        width = if(this.reviewImages.isEmpty()) 500 else this.reviewImages[0].width,
    )
}

fun ReviewAndImage.toFeedData(): Feed {
    try {
        return Feed(
            reviewId = this.review.reviewId,
            userId = this.review.userId ?: 0,
            name = this.review.userName ?: "",
            restaurantName = this.review.restaurantName ?: "",
            rating = this.review.rating ?: 0f,
            profilePictureUrl = BuildConfig.PROFILE_IMAGE_SERVER_URL + this.review.profilePicUrl,
            likeAmount = this.review.likeAmount ?: 0,
            commentAmount = this.review.commentAmount ?: 0,
            isLike = this.like != null,
            isFavorite = this.favorite != null,
            contents = this.review.contents ?: "",
            reviewImages = this.images.map {
                FeedImage(
                    BuildConfig.REVIEW_IMAGE_SERVER_URL + it.pictureUrl,
                    it.width ?: 0,
                    it.height ?: 0
                )
            },
            restaurantId = this.review.restaurantId ?: -1,
            createDate = this.review.createDate ?: ""
        )
    } catch (e: Exception) {
        Log.e(TAG, e.message.toString())
        Log.e(TAG, GsonBuilder().setPrettyPrinting().create().toJson(this))
        throw e
    }
}