package com.sarang.torang.di.feed_di

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.sarang.torang.compose.feed.state.RefreshIndicatorState
import com.sarang.torang.compose.feed.type.pullToRefreshLayoutType
import com.sarang.torang.di.pulltorefresh.PullToRefreshData
import com.sarang.torang.di.pulltorefresh.providePullToRefresh
import com.sryang.library.pullrefresh.rememberPullToRefreshState

val customPullToRefresh: pullToRefreshLayoutType = { data ->
    // FeedScreen에 Scaffold가 있어 modifier을 설정하면 위에 공간이 생김.

    val indicatorState by data.pullToRefreshLayoutState.refreshIndicatorState
    val state = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        snapshotFlow {
            state.refreshIndicatorState.value
        }.collect {
            when(it){
                com.sryang.library.pullrefresh.RefreshIndicatorState.Default->{
                    data.pullToRefreshLayoutState.updateState(RefreshIndicatorState.Default)
                }
                com.sryang.library.pullrefresh.RefreshIndicatorState.Refreshing->{
                    //data.pullToRefreshLayoutState.updateState(RefreshIndicatorState.Refreshing)
                }
                com.sryang.library.pullrefresh.RefreshIndicatorState.ReachedThreshold->{
                    data.pullToRefreshLayoutState.updateState(RefreshIndicatorState.ReachedThreshold)
                }
                com.sryang.library.pullrefresh.RefreshIndicatorState.PullingDown->{
                    data.pullToRefreshLayoutState.updateState(RefreshIndicatorState.PullingDown)
                }
            }
        }
    }

    providePullToRefresh(state).invoke(
        PullToRefreshData(modifier   = data.modifier,
                               state      = indicatorState ,
                               onRefresh  = data.onRefresh,
                               contents   = data.contents))
}

val customPullToRefreshforRestaurantReview: pullToRefreshLayoutType = { data ->
    // FeedScreen에 Scaffold가 있어 modifier을 설정하면 위에 공간이 생김.

    val indicatorState by data.pullToRefreshLayoutState.refreshIndicatorState

    providePullToRefresh(rememberPullToRefreshState()).invoke(
        PullToRefreshData(
            modifier = Modifier, // 음식점 상세의 review에는 scaffold의 padding을 적용하면 위에 공간이 생김
            state = indicatorState ,
            onRefresh = data.onRefresh,
            contents = data.contents
        )
    )
}