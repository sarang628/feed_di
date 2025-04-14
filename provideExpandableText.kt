package com.sarang.torang.di.feed_di

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sryang.library.ExpandableText

fun provideExpandableText(): @Composable (Modifier, String, String, () -> Unit) -> Unit =
    { modifier, nickName, text, onProfile ->
        ExpandableText(
            modifier = modifier,
            nickName = nickName,
            text = text,
            onClickNickName = onProfile
        )
    }