package com.example.news.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.example.news.MainActivity
import com.example.news.databinding.ActivityDetailBinding
import com.example.news.db.AppDatabase
import com.example.news.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsActivity : FragmentActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getSerializableExtra("article") as Article?

        article?.url?.let { binding.webView.loadUrl(it) }

        binding.saveButton.setOnClickListener {
            val article = Article(
                url = article?.url ?: "",
                title = article?.title ?: "",
                description = article?.description ?: "",
                urlToImage = article?.urlToImage ?: "",
                )
            saveArticleToDatabase(article)
        }
        binding.back.setOnClickListener {
            finish()
        }
    }
    private fun saveArticleToDatabase(article: Article) {
        val database = AppDatabase.getDatabase(this)
        lifecycleScope.launch(Dispatchers.IO) {
            database.articleDao().insertArticle(article)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@DetailsActivity, "Article saved successfully!", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this@DetailsActivity, MainActivity::class.java)
            startActivity(intent)

        }
    }
}