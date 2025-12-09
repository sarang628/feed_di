package com.sarang.torang.di.feed_di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.torang.compose.feed.FeedScreenInMain
import com.sarang.torang.compose.feed.type.LocalFeedCompose
import com.sarang.torang.viewmodels.FeedScreenInMainViewModel

@Composable
fun provideFeedScreenInMain(feedsViewModel: FeedScreenInMainViewModel = hiltViewModel()) {
    CompositionLocalProvider(LocalFeedCompose provides CustomFeedCompose) {
        FeedScreenInMain(feedsViewModel = feedsViewModel)
    }
}