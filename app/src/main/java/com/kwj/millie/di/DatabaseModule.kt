package com.kwj.millie.di

import android.app.Application
import androidx.room.Room
import com.kwj.data.source.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt 모듈로 정의된 DatabaseModule 클래스 입니다.
 * 이 모듈은 애플리케이션에 필요한 데이터베이스 관련 서비스의 의존성 주입을 관리 합니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, NewsDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}
