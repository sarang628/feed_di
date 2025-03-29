package com.sarang.torang.di.feed_di

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sryang.library.BottomDetectingLazyColumn


fun provideBottonDetectingLazyColumn(): @Composable (
    Modifier,
    Int,
    () -> Unit,
    @Composable (Int) -> Unit,
    Boolean,
    Arrangement.Vertical,
    LazyListState,
    @Composable (() -> Unit)?
) -> Unit {
    return { modifier, count, onBottom, itemCompose, userScrollEnabled, verticalArrangement, listState, contents ->
        BottomDetectingLazyColumn(
            modifier = modifier,
            items = count,
            onBottom = { onBottom.invoke() },
            userScrollEnabled = userScrollEnabled,
            verticalArrangement = verticalArrangement,
            listState = listState,
            contents = {
                itemCompose.invoke(it)
            }
        )
    }
}