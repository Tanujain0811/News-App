package com.example.news.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.news.repo.NewsRepository
import kotlinx.coroutines.Dispatchers

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    fun getNews(apiKey: String) = liveData(Dispatchers.IO) {
        try {
            val response = repository.fetchNews(apiKey)
            if(!response.isSuccessful){
                println("Error: ${response.errorBody()?.string()}")
            }
            emit(response)
        } catch (e: Exception) {
            emit(null)
        }
    }
}