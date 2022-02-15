package com.shubham.newsapiclientproject2.data.repository

import com.shubham.newsapiclientproject2.data.model.APIResponse
import com.shubham.newsapiclientproject2.data.model.Article
import com.shubham.newsapiclientproject2.data.repository.dataSource.NewsRemoteDataSource
import com.shubham.newsapiclientproject2.data.util.Resource
import com.shubham.newsapiclientproject2.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
): NewsRepository {

<<<<<<< HEAD
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse> {

        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
=======
    override suspend fun getNewsHeadlines(): Resource<APIResponse> {

        return responseToResource(newsRemoteDataSource.getTopHeadlines())
>>>>>>> 6db6fd57e0a6286ef51ac320f8a271cee7f7920d
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {

        if(response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}