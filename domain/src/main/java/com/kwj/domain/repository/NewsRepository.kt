package com.kwj.domain.repository

import com.kwj.domain.base.Result
import com.kwj.domain.model.NewsItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlines(): Flow<Result<List<NewsItem>>>
    suspend fun saveClickedItem(newsItem: NewsItem)
}