package com.kwj.data.model.mapper

import com.kwj.data.model.NewsResponse
import com.kwj.domain.model.NewsItem

/**
 * API Source로 받은 NewsResponse의 Article을 도메인 모델인 NewsItem으로 변환하는 역할을 하는 Mapper 클래스 입니다.
 * 변환된 모델은 애플리케이션 내부에서 사용됩니다.
 *
 * Mapper 클래스는 각 계층 간 결합도를 낮추는데 기여하며 아키텍처에서 중요한 역할을 합니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
fun List<NewsResponse.Article>.mapperToNewsList(): List<NewsItem> {
    val newsList = arrayListOf<NewsItem>()
    this.map { article ->
        newsList.add(NewsItem(article.title, article.urlToImage, article.publishedAt))
    }
    return newsList
}