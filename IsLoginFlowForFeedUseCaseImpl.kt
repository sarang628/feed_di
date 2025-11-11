package com.sarang.torang.di.feed_di

import com.sarang.torang.core.database.dao.LoggedInUserDao
import com.sarang.torang.usecase.IsLoginFlowForFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@InstallIn(SingletonComponent::class)
@Module
class IsLoginFlowForFeedUseCaseImpl {
    @Provides
    fun isLoginFlowUseCase(
        loggedInUserDao: LoggedInUserDao,
    ): IsLoginFlowForFeedUseCase {
        return object : IsLoginFlowForFeedUseCase {
            override val isLogin: Flow<Boolean>
                get() = loggedInUserDao.getLoggedInUserFlow().map { it != null }
        }
    }
}