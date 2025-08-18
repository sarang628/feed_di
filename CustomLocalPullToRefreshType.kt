package com.sarang.torang.di.feed_di

import com.sarang.torang.compose.feed.pullToRefreshLayoutType
import com.sarang.torang.di.pulltorefresh.providePullToRefresh
import com.sryang.library.pullrefresh.rememberPullToRefreshState

val CustomLocalPullToRefreshType: pullToRefreshLayoutType = { isRefreshing, onRefresh, contents ->
    providePullToRefresh(rememberPullToRefreshState()).invoke(isRefreshing, onRefresh, contents)
}