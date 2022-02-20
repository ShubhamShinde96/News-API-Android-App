package com.shubham.newsapiclientproject2.data.repository

import com.shubham.newsapiclientproject2.data.db.ArticleDAO
import com.shubham.newsapiclientproject2.data.model.APIResponse
import com.shubham.newsapiclientproject2.data.model.Article
import com.shubham.newsapiclientproject2.data.repository.dataSource.NewsLocalDataSource
import com.shubham.newsapiclientproject2.data.repository.dataSource.NewsRemoteDataSource
import com.shubham.newsapiclientproject2.data.util.Resource
import com.shubham.newsapiclientproject2.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
): NewsRepository {


    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse> {

        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {

        return responseToResource(newsRemoteDataSource.getSearchedNews(country, searchQuery, page))
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {

        if(response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }

    override suspend fun saveNews(article: Article) {

        newsLocalDataSource.saveArticleToDB(article)
    }

    override suspend fun deleteNews(article: Article) {

        newsLocalDataSource.deleteArticle(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {

        return newsLocalDataSource.getSavedArticles()
    }
}