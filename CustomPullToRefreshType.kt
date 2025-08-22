package com.sarang.torang.di.feed_di

import com.sarang.torang.compose.feed.pullToRefreshLayoutType
import com.sarang.torang.di.pulltorefresh.providePullToRefresh
import com.sryang.library.pullrefresh.rememberPullToRefreshState

val CustomPullToRefreshType: pullToRefreshLayoutType = { modifier, isRefreshing, onRefresh, contents ->
    providePullToRefresh(rememberPullToRefreshState()).invoke(modifier, isRefreshing, onRefresh, contents)
}