package com.shubham.newsapiclientproject2.data.repository.dataSourceImpl

import com.shubham.newsapiclientproject2.data.api.NewsAPIService
import com.shubham.newsapiclientproject2.data.model.APIResponse
import com.shubham.newsapiclientproject2.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
<<<<<<< HEAD
    private val newsAPIService: NewsAPIService
): NewsRemoteDataSource {

    override suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse> {
=======
    private val newsAPIService: NewsAPIService,
    private val country: String,
    private val page: Int
): NewsRemoteDataSource {

    override suspend fun getTopHeadlines(): Response<APIResponse> {
>>>>>>> 6db6fd57e0a6286ef51ac320f8a271cee7f7920d
        return newsAPIService.getTopHeadlines(country, page)
    }


}