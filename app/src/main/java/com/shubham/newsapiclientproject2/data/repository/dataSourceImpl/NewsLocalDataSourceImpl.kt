package com.shubham.newsapiclientproject2.data.repository.dataSourceImpl

import com.shubham.newsapiclientproject2.data.db.ArticleDAO
import com.shubham.newsapiclientproject2.data.model.Article
import com.shubham.newsapiclientproject2.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
): NewsLocalDataSource {

    override suspend fun saveArticleToDB(article: Article) {
        articleDAO.insert(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDAO.getAllArticles()
    }

    override suspend fun deleteArticle(article: Article) {

        return articleDAO.deleteArticle(article)
    }
}