package com.kwj.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kwj.data.model.NewsResponse
import com.kwj.data.source.db.dao.NewsDao

/**
 * Room 라이브러리의 데이터베이스 인스턴스를 정의하는 추상 클래스 입니다.
 * 이 클래스에서 애플리케이션의 데이터베이스 구성, 버전 관리 및 DAO 인터페이스의 접근 방법을 설정 합니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
@Database(
    version = 1,
    entities = [NewsResponse.Article::class],
    exportSchema = false
)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}