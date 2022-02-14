package com.shubham.newsapiclientproject2.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {

    private lateinit var newsAPIService: NewsAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {

        server = MockWebServer()
        newsAPIService = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }


    @Test
    fun getTopHeadlines_sentRequest_receivedExpected() {

        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = newsAPIService.getTopHeadlines("us", 1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=8f1d994693494131901a7bfd1f5ea541")

            // for the mock web server we are not using base url, that's why we have not even used it here to check equality, check on line no 24
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize() {

        runBlocking {

            enqueueMockResponse("newsresponse.json")
            val responseBody = newsAPIService.getTopHeadlines("us", 1).body()
            val articleList = responseBody!!.articles
            assertThat(articleList.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent() {

        runBlocking {

            enqueueMockResponse("newsresponse.json")
            val responseBody = newsAPIService.getTopHeadlines("us", 1).body()
            val articleList = responseBody!!.articles
            var article = articleList[0]
            assertThat(article.author).isEqualTo("Dakin Andone, CNN")
            assertThat(article.url).isEqualTo("https://www.cnn.com/2022/02/14/us/curtis-reeves-trial-florida/index.html")
            assertThat(article.publishedAt).isEqualTo("2022-02-14T11:49:00Z")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}