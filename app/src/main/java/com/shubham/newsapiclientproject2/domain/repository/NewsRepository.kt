package com.shubham.newsapiclientproject2.domain.repository

import androidx.lifecycle.LiveData
import com.shubham.newsapiclientproject2.data.model.APIResponse
import com.shubham.newsapiclientproject2.data.model.Article
import com.shubham.newsapiclientproject2.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    // Network Operations
    suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse>

    suspend fun getSearchedNews(country: String, searchQuery: String, page: Int): Resource<APIResponse>

    // Local DB operations
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
//    suspend fun getSavedNews(): LiveData<List<Article>>
    fun getSavedNews(): Flow<List<Article>>

}