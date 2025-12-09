package com.sarang.torang.di.feed_di.usecase

import com.sarang.torang.di.feed_di.toFeedData
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.uistate.FeedUiState
import com.sarang.torang.usecase.GetFeedByPictureIdFlowUseCase
import com.sarang.torang.usecase.IsLoginFlowForFeedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

@InstallIn(SingletonComponent::class)
@Module
class GetFeedByPictureIdFlowUseCaseImpl {
    val tag = "__GetFeedByRestaurantIdFlowUseCaseImpl"

    @Provides
    fun provideGetFeedByPictureIdFlowUseCase(
        feedRepository: FeedRepository,
        loginFlowForFeedUseCase: IsLoginFlowForFeedUseCase
    ): GetFeedByPictureIdFlowUseCase {
        return object : GetFeedByPictureIdFlowUseCase {
            override fun invoke(pictureId: Int?): Flow<FeedUiState> {
                if(pictureId == null) return MutableStateFlow(FeedUiState())
                //TODO:: 사진 아이디로 리뷰 가져오기 (repository 개발 완료 최신버전 적용하기)
                return combine(
                    feedRepository.findByPictureIdFlow(pictureId = pictureId),
                    loginFlowForFeedUseCase.isLogin
                )
                { feed, isLogin ->
                    FeedUiState(
                        list = feed?.let { listOf(it.toFeedData()) } ?: run { emptyList() },
                        isLogin = isLogin
                    )
                }
            }
        }
    }
}