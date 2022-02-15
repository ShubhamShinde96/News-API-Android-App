package com.shubham.newsapiclientproject2.data.repository.dataSource

import com.shubham.newsapiclientproject2.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsRemoteDataSource {

<<<<<<< HEAD
    suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse>
=======
    suspend fun getTopHeadlines(): Response<APIResponse>
>>>>>>> 6db6fd57e0a6286ef51ac320f8a271cee7f7920d

}