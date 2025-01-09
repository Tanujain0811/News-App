package com.example.news.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.MainActivity
import com.example.news.adaptres.NewsAdapter
import com.example.news.databinding.FragmentSavedBinding
import com.example.news.db.AppDatabase
import com.example.news.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding
    private val adapter = NewsAdapter(
        onItemClicked = { article ->
            val intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra("article", article)
            }
            startActivity(intent)
        },
        onDeleteClicked = { article ->
            deleteArticleToDatabase(article)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.savedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.savedRecyclerView.adapter = adapter

        loadSavedArticles(adapter)
    }

    private fun loadSavedArticles(adapter: NewsAdapter) {
        val database = AppDatabase.getDatabase(requireContext())
        lifecycleScope.launch(Dispatchers.IO) {
            val savedArticles = database.articleDao().getAllArticles()
            withContext(Dispatchers.Main) {
                adapter.submitList(savedArticles,true)
            }
        }
    }
    private fun deleteArticleToDatabase(article: Article) {
        val database = context?.let { AppDatabase.getDatabase(it) }
        lifecycleScope.launch(Dispatchers.IO) {
            database?.articleDao()?.deleteArticle(article)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Article deleted successfully!", Toast.LENGTH_SHORT).show()
                loadSavedArticles(adapter)

            }
        }
    }
}