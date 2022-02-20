package com.shubham.newsapiclientproject2.presentation.di

import com.shubham.newsapiclientproject2.data.db.ArticleDAO
import com.shubham.newsapiclientproject2.data.repository.dataSource.NewsLocalDataSource
import com.shubham.newsapiclientproject2.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {


    @Singleton
    @Provides
    fun provideNewsLocalDataSource(articleDAO: ArticleDAO): NewsLocalDataSource {

        return NewsLocalDataSourceImpl(articleDAO)
    }


}