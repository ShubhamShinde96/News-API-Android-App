package com.shubham.newsapiclientproject2.presentation.di

import com.shubham.newsapiclientproject2.data.api.NewsAPIService
import com.shubham.newsapiclientproject2.data.repository.dataSource.NewsRemoteDataSource
import com.shubham.newsapiclientproject2.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsAPIService: NewsAPIService): NewsRemoteDataSource {

        return NewsRemoteDataSourceImpl(newsAPIService)
    }

}