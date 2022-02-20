package com.shubham.newsapiclientproject2.presentation.di

import com.shubham.newsapiclientproject2.data.repository.NewsRepositoryImpl
import com.shubham.newsapiclientproject2.data.repository.dataSource.NewsLocalDataSource
import com.shubham.newsapiclientproject2.data.repository.dataSource.NewsRemoteDataSource
import com.shubham.newsapiclientproject2.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository {

        return NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)
    }

}