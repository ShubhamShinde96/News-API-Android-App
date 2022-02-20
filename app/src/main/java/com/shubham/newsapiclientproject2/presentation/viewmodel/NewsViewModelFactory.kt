package com.shubham.newsapiclientproject2.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubham.newsapiclientproject2.domain.usecase.GetNewsHeadlinesUseCase
import com.shubham.newsapiclientproject2.domain.usecase.GetSavedNewsUseCase
import com.shubham.newsapiclientproject2.domain.usecase.GetSearchedNewsUseCase
import com.shubham.newsapiclientproject2.domain.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return NewsViewModel(app, getNewsHeadlineUseCase, getSearchedNewsUseCase, saveNewsUseCase, getSavedNewsUseCase) as T
    }

}