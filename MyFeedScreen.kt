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
) {
    val listState = rememberLazyListState()
    var scrollEnabled by remember { mutableStateOf(true) }

    MyFeedScreen(
        reviewId = reviewId,
        onBack = onBack,
        listState = listState,
        feed = {
            Feed(
                review = it.review { },
                isZooming = { scrollEnabled = !it },
                progressTintColor = progressTintColor
            )
        }
    )
}
