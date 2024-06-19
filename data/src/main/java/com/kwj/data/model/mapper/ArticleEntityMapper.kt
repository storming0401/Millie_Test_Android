package com.kwj.data.model.mapper

import android.content.Context
import com.kwj.common.log.MillieLogger
import com.kwj.data.model.NewsResponse
import com.kwj.data.source.db.entity.ArticleEntity
import com.kwj.common.util.downloadAndSaveImage
import com.kwj.domain.model.NewsItem

/**
 * DB에 getter/setter 하기 위해 ArticleEntity 객체로 변환 및 NewsItem으로 변환하는 역할을 하는 Mapper 클래스 입니다.
 * 변환된 모델은 애플리케이션 내부에서 사용됩니다.
 *
 * Mapper 클래스는 각 계층 간 결합도를 낮추는데 기여하며 아키텍처에서 중요한 역할을 합니다.
 *
 * @author (김위진)
 * @since (2024-06-18)
 */
suspend fun List<NewsResponse.Article>.mapperToArticleEntitys(context: Context) : List<ArticleEntity> {
    val articleEntities = arrayListOf<ArticleEntity>()
    this.map { article ->
        val fileName = article.urlToImage?.extractFileName() ?: ""

        articleEntities.add(
            ArticleEntity(
                fileName,
                article.urlToImage.extractFilePath(context, fileName),
                article.title,
                article.publishedAt,
                article.url,
                false
            )
        )
    }
    return articleEntities
}

private fun String.extractFileName() = this.substringAfterLast('/')

private suspend fun String?.extractFilePath(context: Context, fileName: String): String {
    if (this == null) return ""
    return downloadAndSaveImage(context, this, fileName) ?: ""
}

fun List<ArticleEntity>.mapperToNewsList() : List<NewsItem> {
    val newsList = arrayListOf<NewsItem>()
    MillieLogger.d("mapperToNewsList ArticleEntity size >>> ${this.size}")
    this.map { article ->
        MillieLogger.d("mapperToNewsList ArticleEntity >>> ${article.title}")
        newsList.add(
            NewsItem(
                article.filePath,
                article.title,
                article.publishedAt,
                article.url,
                false
            )
        )
    }
    return newsList
}