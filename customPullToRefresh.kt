package com.sarang.torang.di.feed_di

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.sarang.torang.compose.feed.state.RefreshIndicatorState
import com.sarang.torang.compose.feed.type.pullToRefreshLayoutType
import com.sarang.torang.di.pulltorefresh.PullToRefreshData
import com.sarang.torang.di.pulltorefresh.providePullToRefresh
import com.sryang.library.pullrefresh.rememberPullToRefreshState

val customPullToRefresh: pullToRefreshLayoutType = { modifier, pullToRefreshLayoutState, onRefresh, contents ->
    // FeedScreen에 Scaffold가 있어 modifier을 설정하면 위에 공간이 생김.

    val indicatorState by pullToRefreshLayoutState.refreshIndicatorState
    val state = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        snapshotFlow {
            state.refreshIndicatorState.value
        }.collect {
            Log.d("__customPullToRefresh", "${it}")
            when(it){
                com.sryang.library.pullrefresh.RefreshIndicatorState.Default->{
                    pullToRefreshLayoutState.updateState(RefreshIndicatorState.Default)
                }
                com.sryang.library.pullrefresh.RefreshIndicatorState.Refreshing->{
                    //pullToRefreshLayoutState.updateState(RefreshIndicatorState.Refreshing)
                }
                com.sryang.library.pullrefresh.RefreshIndicatorState.ReachedThreshold->{
                    pullToRefreshLayoutState.updateState(RefreshIndicatorState.ReachedThreshold)
                }
                com.sryang.library.pullrefresh.RefreshIndicatorState.PullingDown->{
                    pullToRefreshLayoutState.updateState(RefreshIndicatorState.PullingDown)
                }
            }
        }
    }

    providePullToRefresh(state).invoke(
        PullToRefreshData(modifier   = modifier,
                               state      = indicatorState ,
                               onRefresh  = onRefresh,
                               contents   = contents))
}

val customPullToRefreshforRestaurantReview: pullToRefreshLayoutType = { modifier, pullToRefreshLayoutState, onRefresh, contents ->
    // FeedScreen에 Scaffold가 있어 modifier을 설정하면 위에 공간이 생김.

    val indicatorState by pullToRefreshLayoutState.refreshIndicatorState

    providePullToRefresh(rememberPullToRefreshState()).invoke(
        PullToRefreshData(
            modifier = Modifier, // 음식점 상세의 review에는 scaffold의 padding을 적용하면 위에 공간이 생김
            state = indicatorState ,
            onRefresh = onRefresh,
            contents = contents
        )
    )
}