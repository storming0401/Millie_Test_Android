package com.kwj.data.source.db.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 'article' 테이블을 위한 엔티티 클래스 입니다.
 * Room 라이브러리의 @Entity 어노테이션을 사용하여 데이터베이스 테이블과 매핑됩니다.
 * 클래스의 각 필드는 테이블의 컬럼에 해당하며, Room 라이브러리는 이 클래스를 사용하여 데이터베이스 작업을 수행합니다.
 *
 * @author (김위진)
 * @since (2024-06-18)
 */
@Entity(tableName = "article")
class ArticleEntity(
    @PrimaryKey val title: String,
    val urlToImage: String?,
    val publishedAt: String,
    val url: String,
    var isVisited: Boolean = false
)