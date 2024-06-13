package com.kwj.data.source.remote

import com.kwj.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 네트워크 통신을 위한 API 인터페이스입니다.
 * 이 인터페이스는 Retrofit을 사용하여 HTTP API 호출을 추상화합니다.
 * 각 메소드는 서버에 특정 데이터를 요청하고 응답을 받기 위한 HTTP 요청 명세를 정의합니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
interface ApiService {
    @GET("/top-headlines")
    suspend fun getTopHeadLines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}