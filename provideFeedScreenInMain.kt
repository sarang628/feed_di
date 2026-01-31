package com.sarang.torang.di.feed_di

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.sarang.torang.compose.feed.MainFeedScreen
import com.sarang.torang.viewmodels.FeedScreenInMainViewModel

@Composable
fun provideFeedScreenInMain(feedsViewModel: FeedScreenInMainViewModel = hiltViewModel()) {
        MainFeedScreen(feed                 = CustomFeedCompose,
                       pullToRefresh        = customPullToRefresh,
                       bottomDetectColumn   = CustomBottomDetectingLazyColumnType,
                       feedsViewModel       = feedsViewModel)
}