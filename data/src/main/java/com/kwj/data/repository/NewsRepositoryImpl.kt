package com.kwj.data.repository

import com.kwj.common.log.MillieLogger
import com.kwj.data.model.mapper.mapperToNewsList
import com.kwj.data.model.mapper.mappertoArticleEntitys
import com.kwj.data.source.db.dao.ArticleDao
import com.kwj.data.source.remote.ApiService
import com.kwj.data.util.API_KEY
import com.kwj.data.util.COUNTRY_KR
import com.kwj.domain.base.Result
import com.kwj.domain.model.NewsItem
import com.kwj.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * NewsRepository 의 구현체 클래스 입니다.
 * 이 클래스는 네트워크에서 데이터를 가져오고, 앱에서 사용할 수 있도록 데이터를 처리하는 책임을 지고 있습니다.
 * ApiService의 API 응답을 도메인 모델로 매핑하고, 필요한 비즈니스 로직을 적용합니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val articleDao: ArticleDao,
) : NewsRepository {

    override suspend fun getTopHeadlines(): Flow<Result<List<NewsItem>>> = flow {
        val response = apiService.getTopHeadLines(COUNTRY_KR, API_KEY)
        articleDao.insertAll(response.articles.mappertoArticleEntitys())
        emit(Result.Success(response.articles.mapperToNewsList()))

    }.catch { e ->
        MillieLogger.e("[ERROR] getTopHeadlines : ${e.message}")
        emit(Result.Success(articleDao.getAll().mapperToNewsList()))
    }
}