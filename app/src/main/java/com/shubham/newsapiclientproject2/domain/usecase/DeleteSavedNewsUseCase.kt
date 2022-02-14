package com.shubham.newsapiclientproject2.domain.usecase

import com.shubham.newsapiclientproject2.data.model.Article
import com.shubham.newsapiclientproject2.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) = newsRepository.deleteNews(article)

}