package com.example.news.repo

import com.example.news.network.NewsApiService

class NewsRepository(private val apiService: NewsApiService) {
    suspend fun fetchNews(apiKey: String) = apiService.getTopHeadlines("us",apiKey)
}
