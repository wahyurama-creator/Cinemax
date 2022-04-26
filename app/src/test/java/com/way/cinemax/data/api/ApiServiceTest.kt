package com.way.cinemax.data.api

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {
    private lateinit var apiService: ApiService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(
        fileName: String,
        server: MockWebServer
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getPopularMovie_sentRequest_receivedExcepted() {
        runBlocking {
            enqueueMockResponse("popular_movie.json", server)
            val responseBody = apiService.getPopularMovie(page = 1).body()
            val request = server.takeRequest()
            Truth.assertThat(responseBody).isNotNull()
            Truth.assertThat(request.path)
                .isEqualTo("/3/movie/popular?api_key=00e220d0885743881b6f1ea9a8cf52f2&language=id-ID&page=1&region=ID")
        }
    }

    @Test
    fun getSearchMovie_sentRequest_receivedExcepted() {
        runBlocking {
            enqueueMockResponse("search_movie.json", server)
            val responseBody = apiService.getSearchMovie(searchQuery = "avengers", page = 1).body()
            val request = server.takeRequest()
            Truth.assertThat(responseBody).isNotNull()
            Truth.assertThat(request.path)
                .isEqualTo("/3/search/movie?api_key=00e220d0885743881b6f1ea9a8cf52f2&language=id-ID&query=avengers&page=1&region=ID")
        }
    }

    @Test
    fun getPopularMovie_receivedResponse_correctPageSize() {
        runBlocking {
            enqueueMockResponse("popular_movie.json", server)
            val responseBody = apiService.getPopularMovie(page = 1).body()
            val moviesSize = responseBody?.movies
            Truth.assertThat(moviesSize?.size).isNotNull()
            Truth.assertThat(moviesSize?.size).isEqualTo(20)
        }
    }

    @Test
    fun getSearchMovie_receivedResponse_correctPageSize() {
        runBlocking {
            enqueueMockResponse("search_movie.json", server)
            val responseBody = apiService.getSearchMovie(searchQuery = "avengers", page = 1).body()
            val moviesSize = responseBody?.movies
            Truth.assertThat(moviesSize?.size).isNotNull()
            Truth.assertThat(moviesSize?.size).isEqualTo(20)
        }
    }

    @Test
    fun getPopularMovie_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("popular_movie.json", server)
            val responseBody = apiService.getPopularMovie(page = 1).body()
            val moviesSize = responseBody?.movies?.get(0)
            Truth.assertThat(moviesSize).isNotNull()
            Truth.assertThat(moviesSize?.id).isEqualTo(414906)
            Truth.assertThat(moviesSize?.title).isEqualTo("The Batman")
            Truth.assertThat(moviesSize?.adult).isEqualTo(false)
            Truth.assertThat(moviesSize?.backdrop_path)
                .isEqualTo("/5P8SmMzSNYikXpxil6BYzJ16611.jpg")
            Truth.assertThat(moviesSize?.poster_path).isEqualTo("/3VFI3zbuNhXzx7dIbYdmvBLekyB.jpg")
            Truth.assertThat(moviesSize?.original_language).isEqualTo("en")
            Truth.assertThat(moviesSize?.release_date).isEqualTo("2022-03-02")
            Truth.assertThat(moviesSize?.overview).isEqualTo("")
            Truth.assertThat(moviesSize?.genre_ids).isEqualTo(
                listOf(
                    80,
                    9648,
                    53
                )
            )
            Truth.assertThat(moviesSize?.video).isFalse()
        }
    }
}