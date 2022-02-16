package com.shubham.newsapiclientproject2.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubham.newsapiclientproject2.domain.usecase.GetNewsHeadlinesUseCase

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlinesUseCase,
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return NewsViewModel(app, getNewsHeadlineUseCase) as T
    }

}