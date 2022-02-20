package com.shubham.newsapiclientproject2.presentation.di

import com.shubham.newsapiclientproject2.domain.repository.NewsRepository
import com.shubham.newsapiclientproject2.domain.usecase.GetNewsHeadlinesUseCase
import com.shubham.newsapiclientproject2.domain.usecase.GetSearchedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsHeadlineUseCase(newsRepository: NewsRepository): GetNewsHeadlinesUseCase {

        return GetNewsHeadlinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedNewsUseCase(newsRepository: NewsRepository): GetSearchedNewsUseCase {

        return GetSearchedNewsUseCase(newsRepository)
    }

}