package com.shubham.newsapiclientproject2.data.repository.dataSource

import com.shubham.newsapiclientproject2.data.model.Article

interface NewsLocalDataSource {

    suspend fun saveArticleToDB(article: Article)

}