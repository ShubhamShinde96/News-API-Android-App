package com.shubham.newsapiclientproject2.presentation.di

import android.app.Application
import com.shubham.newsapiclientproject2.domain.usecase.GetNewsHeadlinesUseCase
import com.shubham.newsapiclientproject2.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
    ): NewsViewModelFactory {

        return NewsViewModelFactory(application, getNewsHeadlinesUseCase)
    }

}