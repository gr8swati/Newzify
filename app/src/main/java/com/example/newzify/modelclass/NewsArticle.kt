package com.example.newzify.modelclass

data class NewsArticle(
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?
)

data class NewsResponse(
    val articles: List<NewsArticle>
)