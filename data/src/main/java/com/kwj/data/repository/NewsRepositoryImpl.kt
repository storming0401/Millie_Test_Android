package com.kwj.data.repository

import com.kwj.data.source.remote.ApiService
import com.kwj.domain.repository.NewsRepository
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
) : NewsRepository {

    override fun getTopHeadlines() {
        TODO("Not yet implemented")
    }
}