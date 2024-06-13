package com.kwj.millie.di

import com.kwj.data.source.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt 모듈로 정의된 DaoModule 클래스 입니다.
 * 이 모듈은 애플리케이션에서 데이터 엑세스 오브젝트(DAO)의 종속성을 관리하는 모듈입니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun provideNewsDao(database: NewsDatabase) = database.newsDao()
}