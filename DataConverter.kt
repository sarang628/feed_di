package com.sarang.torang.di.feed_di

import android.util.Log
import com.google.gson.GsonBuilder
import com.sarang.torang.BuildConfig
import com.sarang.torang.data.basefeed.Restaurant
import com.sarang.torang.data.basefeed.Review
import com.sarang.torang.data.basefeed.User
import com.sarang.torang.data.entity.ReviewAndImageEntity
import com.sarang.torang.data.feed.Feed
import com.sarang.torang.data.feed.FeedImage

private const val TAG = "__DataConverter"
fun Feed.toReview(): Review {
    return Review(
        reviewId = this.reviewId,
        reviewImages = this.reviewImages.map { it.url },
        user = User(
            name = this.name,
            profilePictureUrl = this.profilePictureUrl,
            userId = this.userId
        ),
        restaurant = Restaurant(
            restaurantId = this.restaurantId,
            restaurantName = this.restaurantName
        ),
        rating = this.rating,
        likeAmount = this.likeAmount,
        commentAmount = this.commentAmount,
        comments = null,
        isLike = this.isLike,
        isFavorite = this.isFavorite,
        contents = this.contents,
        createDate = createDate
    )
}

fun ReviewAndImageEntity.toFeedData(): Feed {
    try {
        return Feed(
            reviewId = this.review.reviewId,
            userId = this.review.userId,
            name = this.review.userName,
            restaurantName = this.review.restaurantName ?: "",
            rating = this.review.rating,
            profilePictureUrl = BuildConfig.PROFILE_IMAGE_SERVER_URL + this.review.profilePicUrl,
            likeAmount = this.review.likeAmount,
            commentAmount = this.review.commentAmount,
            isLike = this.like != null,
            isFavorite = this.favorite != null,
            contents = this.review.contents,
            reviewImages = this.images.map {
                FeedImage(
                    BuildConfig.REVIEW_IMAGE_SERVER_URL + it.pictureUrl,
                    it.width,
                    it.height
                )
            },
            restaurantId = this.review.restaurantId ?: -1,
            createDate = this.review.createDate
        )
    } catch (e: Exception) {
        Log.e(TAG, e.message.toString())
        Log.e(TAG, GsonBuilder().setPrettyPrinting().create().toJson(this))
        throw e
    }
}