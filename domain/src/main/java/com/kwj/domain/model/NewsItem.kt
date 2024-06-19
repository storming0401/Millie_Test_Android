package com.kwj.domain.model

data class NewsItem(
    val fileName: String?,
    val filePath: String?,
    val title: String,
    val publishedDate: String,
    val url: String,
    val isClicked: Boolean
)