package com.shubham.newsapiclientproject2.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shubham.newsapiclientproject2.data.model.APIResponse
import com.shubham.newsapiclientproject2.data.model.Article
import com.shubham.newsapiclientproject2.data.util.Resource
import com.shubham.newsapiclientproject2.domain.usecase.GetNewsHeadlinesUseCase
import com.shubham.newsapiclientproject2.domain.usecase.GetSearchedNewsUseCase
import com.shubham.newsapiclientproject2.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase
): AndroidViewModel(app)  {

    val newsHeadlines : MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    val searchedNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {

        newsHeadlines.postValue(Resource.Loading())

        try {

            if (isNetworkAvailable(app)) { // to get this context we need to extend androidViewModel instead of viewModel

                val apiResponse = getNewsHeadlinesUseCase.execute(country, page)
                newsHeadlines.postValue(apiResponse)
            } else {
                newsHeadlines.postValue(Resource.Error("Internet is not available!"))
            }

        }catch (e: Exception) {
            newsHeadlines.postValue(Resource.Error(e.message.toString()))
        }
    }

    //search
    fun getSearchedNews(country: String, searchQuery: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {

        searchedNews.postValue(Resource.Loading())

        try {

            if(isNetworkAvailable(app)) {

                val response = getSearchedNewsUseCase.execute(country, searchQuery, page)
                searchedNews.postValue(response)

            } else {

                searchedNews.postValue(Resource.Error("Internet is not available!"))
            }

        } catch (e: Exception) {

            searchedNews.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context): Boolean {

        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }


    // local data
    fun saveArticle(article: Article) = viewModelScope.launch {

        saveNewsUseCase.execute(article)
    }

}






















