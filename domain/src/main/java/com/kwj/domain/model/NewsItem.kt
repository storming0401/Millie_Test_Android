package com.kwj.domain.model

data class NewsItem(
    val imagePath: String?,
    val title: String,
    val publishedDate: String,
    val url: String,
    val isViewed: Boolean
)