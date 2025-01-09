package com.example.news.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey val url: String,
    val title: String,
    val description: String,
    val imageUrl: String
)