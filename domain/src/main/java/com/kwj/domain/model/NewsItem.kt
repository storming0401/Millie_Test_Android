package com.kwj.domain.model

data class NewsItem(
    val title: String,
    val imageUrl: String?,
    val publishedDate: String,
    val url: String,
    val isViewed: Boolean
)