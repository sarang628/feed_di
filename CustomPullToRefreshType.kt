package com.sarang.torang.di.feed_di

import androidx.compose.ui.Modifier
import com.sarang.torang.compose.feed.pullToRefreshLayoutType
import com.sarang.torang.di.pulltorefresh.providePullToRefresh
import com.sryang.library.pullrefresh.rememberPullToRefreshState

val CustomPullToRefreshType: pullToRefreshLayoutType = { modifier, isRefreshing, onRefresh, contents ->
    // FeedScreen에 Scaffold가 있어 modifier을 설정하면 위에 공간이 생김.
    providePullToRefresh(rememberPullToRefreshState()).invoke(modifier, isRefreshing, onRefresh, contents)
}