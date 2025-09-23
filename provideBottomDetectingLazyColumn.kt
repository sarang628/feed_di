package com.sarang.torang.di.feed_di

import androidx.compose.runtime.Composable
import com.sarang.torang.compose.feed.type.BottomDetectingLazyColumnData
import com.sryang.library.BottomDetectingLazyColumn


fun provideBottomDetectingLazyColumn(): @Composable (
    BottomDetectingLazyColumnData
) -> Unit {
    return { data ->
        BottomDetectingLazyColumn(
            modifier = data.modifier,
            items = data.count,
            onBottom = { data.onBottom.invoke() },
            userScrollEnabled = data.userScrollEnabled,
            verticalArrangement = data.arrangement,
            listState = data.listState,
            contents = { data.itemCompose.invoke(it) }
        )
    }
}