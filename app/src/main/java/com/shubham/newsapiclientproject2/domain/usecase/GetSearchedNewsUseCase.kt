package com.shubham.newsapiclientproject2.domain.usecase

import com.shubham.newsapiclientproject2.data.model.APIResponse
import com.shubham.newsapiclientproject2.data.util.Resource
import com.shubham.newsapiclientproject2.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(country:String, searchQuery: String, page: Int): Resource<APIResponse> {

        return newsRepository.getSearchedNews(country, searchQuery, page)
    }

}