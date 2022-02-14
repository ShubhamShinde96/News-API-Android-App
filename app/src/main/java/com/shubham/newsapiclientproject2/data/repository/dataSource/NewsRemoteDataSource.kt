package com.shubham.newsapiclientproject2.data.repository.dataSource

import com.shubham.newsapiclientproject2.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsRemoteDataSource {

    suspend fun getTopHeadlines(): Response<APIResponse>

}