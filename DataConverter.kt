package com.sryang.torang.di.feed_di

import com.sryang.base.feed.data.Restaurant
import com.sryang.base.feed.data.Review
import com.sryang.base.feed.data.User
import com.sryang.torang.data.feed.CommentData
import com.sryang.torang.data.feed.FeedData
import com.sryang.torang_repository.data.RemoteComment
import com.sryang.torang_repository.data.entity.FeedEntity
import com.sryang.torang_repository.data.entity.ReviewAndImageEntity

fun RemoteComment.toCommentData(): CommentData {
    return CommentData(
        userId = this.user.userId,
        profileImageUrl = this.user.profilePicUrl,
        date = this.create_date,
        comment = this.comment,
        name = this.user.userName,
        likeCount = 0
    )
}


fun FeedEntity.review(): Review {
    return Review(
        reviewId = this.reviewId,
        likeAmount = this.likeAmount,
        commentAmount = this.commentAmount,
        isLike = false,
        isFavorite = false,
        contents = this.contents,
        rating = this.rating,
        comments = null,
        restaurant = Restaurant(
            restaurantName = this.restaurantName,
            restaurantId = this.restaurantId,
        ),
        user = User(
            userId = this.userId,
            name = this.userName,
            profilePictureUrl = this.profilePicUrl
        )
    )
}

fun FeedData.review(): Review {
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

fun ReviewAndImageEntity.toFeedData(): FeedData {
    return FeedData(
        reviewId = this.review.reviewId,
        userId = this.review.userId,
        name = this.review.userName,
        restaurantName = this.review.restaurantName,
        rating = this.review.rating,
        profilePictureUrl = this.review.profilePicUrl,
        likeAmount = this.review.likeAmount,
        commentAmount = this.review.commentAmount,
        isLike = this.like != null,
        isFavorite = this.favorite != null,
        contents = this.review.contents,
        reviewImages = this.images.map { it.pictureUrl },
        restaurantId = this.review.restaurantId
    )
}