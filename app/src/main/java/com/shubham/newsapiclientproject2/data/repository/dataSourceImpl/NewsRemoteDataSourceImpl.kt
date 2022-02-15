package com.shubham.newsapiclientproject2.data.repository.dataSourceImpl

import com.shubham.newsapiclientproject2.data.api.NewsAPIService
import com.shubham.newsapiclientproject2.data.model.APIResponse
import com.shubham.newsapiclientproject2.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService
): NewsRemoteDataSource {

    override suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse> {

        return newsAPIService.getTopHeadlines(country, page)
    }


}