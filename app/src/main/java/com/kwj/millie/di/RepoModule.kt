package com.kwj.millie.di

import com.kwj.data.repository.NewsRepositoryImpl
import com.kwj.data.source.db.dao.NewsDao
import com.kwj.data.source.remote.ApiService
import com.kwj.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt 모듈로 정의된 RepoModule 클래스 입니다.
 * 이 모듈은 애플리케이션의 다양한 데이터 저장소 인스턴스를 제공 합니다.
 * Repository 객체 들은 데이터 관련 로직을 캡슐화 하여 관련된 데이터를 처리 합니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        apiService: ApiService,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(apiService, newsDao)
}