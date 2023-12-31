package com.sryang.torang.di.feed_di

import com.sarang.torang.BuildConfig
import com.sryang.torang.data.basefeed.Restaurant
import com.sryang.torang.data.basefeed.Review
import com.sryang.torang.data.basefeed.User
import com.sryang.torang.data.feed.Feed
import com.sryang.torang_repository.data.entity.ReviewAndImageEntity

fun Feed.review(): Review {
    return Review(
        reviewId = this.reviewId,
        reviewImages = this.reviewImages,
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
        contents = this.contents
    )
}

fun ReviewAndImageEntity.toFeedData(): Feed {
    return Feed(
        reviewId = this.review.reviewId,
        userId = this.review.userId,
        name = this.review.userName,
        restaurantName = this.review.restaurantName,
        rating = this.review.rating,
        profilePictureUrl = BuildConfig.PROFILE_IMAGE_SERVER_URL + this.review.profilePicUrl,
        likeAmount = this.review.likeAmount,
        commentAmount = this.review.commentAmount,
        isLike = this.like != null,
        isFavorite = this.favorite != null,
        contents = this.review.contents,
        reviewImages = this.images.map { BuildConfig.REVIEW_IMAGE_SERVER_URL + it.pictureUrl },
        restaurantId = this.review.restaurantId
    )
}