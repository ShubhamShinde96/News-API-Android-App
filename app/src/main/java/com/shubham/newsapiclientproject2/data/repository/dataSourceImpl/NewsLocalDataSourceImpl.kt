package com.shubham.newsapiclientproject2.data.repository.dataSourceImpl

import com.shubham.newsapiclientproject2.data.db.ArticleDAO
import com.shubham.newsapiclientproject2.data.model.Article
import com.shubham.newsapiclientproject2.data.repository.dataSource.NewsLocalDataSource

class NewsLocalDataSourceImpl(
    private val articleDAO: ArticleDAO
): NewsLocalDataSource {

    override suspend fun saveArticleToDB(article: Article) {
        articleDAO.insert(article)
    }
}