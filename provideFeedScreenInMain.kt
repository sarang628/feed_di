package com.sarang.torang.di.feed_di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.torang.compose.feed.FeedScreenInMain
import com.sarang.torang.compose.feed.internal.components.LocalExpandableTextType
import com.sarang.torang.compose.feed.internal.components.LocalFeedImageLoader
import com.sarang.torang.compose.feed.type.LocalBottomDetectingLazyColumnType
import com.sarang.torang.compose.feed.type.LocalFeedCompose
import com.sarang.torang.compose.feed.type.LocalPullToRefreshLayoutType
import com.sarang.torang.di.basefeed_di.CustomExpandableTextType
import com.sarang.torang.di.basefeed_di.CustomFeedImageLoader
import com.sarang.torang.viewmodels.FeedScreenInMainViewModel

@Composable
fun provideFeedScreenInMain(feedsViewModel: FeedScreenInMainViewModel = hiltViewModel()) {
    CompositionLocalProvider(
        LocalFeedCompose provides CustomFeedCompose,
        LocalPullToRefreshLayoutType provides customPullToRefresh,
        LocalExpandableTextType provides CustomExpandableTextType,
        LocalFeedImageLoader provides {CustomFeedImageLoader().invoke(it)},
        LocalBottomDetectingLazyColumnType provides CustomBottomDetectingLazyColumnType
    ) {
        FeedScreenInMain(feedsViewModel = feedsViewModel)
    }
}