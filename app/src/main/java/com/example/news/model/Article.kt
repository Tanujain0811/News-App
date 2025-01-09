package com.example.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Article(
    val title: String,
    val description: String,
    val urlToImage: String,
    @PrimaryKey val url: String
) : Serializable