package com.kwj.data.source.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kwj.data.model.Article

/**
 * News 데이터에 대한 데이터베이스 액세스를 정의하는 인터페이스 입니다.
 * Room 라이브러리를 사용하여 북마크 관련 데이터 작업을 위한 CRUD를 제공합니다.
 * 데이터베이스에서 사용될 쿼리 메서드들을 정의 합니다.
 *
 * @author (김위진)
 * @since (2024-06-13)
 */
@Dao
interface NewsDao {
    @Query("SELECT * FROM article")
    fun getAllArticles(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)
}