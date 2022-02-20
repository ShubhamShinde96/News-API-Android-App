package com.shubham.newsapiclientproject2.data.db

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.shubham.newsapiclientproject2.data.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun getArticleDAO(): ArticleDAO


}


























