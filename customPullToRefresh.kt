package com.sarang.torang.di.feed_di

import androidx.compose.runtime.getValue
import com.sarang.torang.compose.feed.type.pullToRefreshLayoutType
import com.sarang.torang.di.pulltorefresh.providePullToRefresh
import com.sryang.library.pullrefresh.rememberPullToRefreshState

val customPullToRefresh: pullToRefreshLayoutType = { modifier, pullToRefreshLayoutState, onRefresh, contents ->
    // FeedScreen에 Scaffold가 있어 modifier을 설정하면 위에 공간이 생김.

    val indicatorState by pullToRefreshLayoutState.refreshIndicatorState

    providePullToRefresh(rememberPullToRefreshState()).invoke(modifier, indicatorState, onRefresh, contents)
}