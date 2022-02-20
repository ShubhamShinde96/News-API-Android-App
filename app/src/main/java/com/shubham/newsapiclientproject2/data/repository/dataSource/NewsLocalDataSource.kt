package com.shubham.newsapiclientproject2.data.repository.dataSource

import com.shubham.newsapiclientproject2.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    suspend fun saveArticleToDB(article: Article)

    fun getSavedArticles(): Flow<List<Article>>

    suspend fun deleteArticle(article: Article)

}