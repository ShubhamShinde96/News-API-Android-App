package com.shubham.newsapiclientproject2.data.model


import com.google.gson.annotations.SerializedName
import com.shubham.newsapiclientproject2.data.model.Article

data class APIResponse(

    @SerializedName("articles")
    val articles: List<Article>,

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int
)