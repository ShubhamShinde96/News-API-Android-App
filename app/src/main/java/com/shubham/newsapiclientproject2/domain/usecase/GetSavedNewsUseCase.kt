package com.shubham.newsapiclientproject2.domain.usecase

import com.shubham.newsapiclientproject2.data.model.Article
import com.shubham.newsapiclientproject2.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {

    fun execute(): Flow<List<Article>> {

        return newsRepository.getSavedNews()
    }

}