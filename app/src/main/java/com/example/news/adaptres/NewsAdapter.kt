package com.example.news.adaptres



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.model.Article
import com.example.news.databinding.ItemNewsBinding

class NewsAdapter(
    private val onItemClicked: (Article) -> Unit,
    private val onDeleteClicked: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val articles = mutableListOf<Article>()
    private var isSaved = false

    fun submitList(newArticles: List<Article>, isSaved: Boolean) {
        articles.clear()
        articles.addAll(newArticles)
        this.isSaved = isSaved
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.titleTextView.text = article.title
            binding.descriptionTextView.text = article.description
            Glide.with(binding.root.context).load(article.urlToImage).into(binding.newsImageView)
            if(isSaved){
                binding.delete.visibility = View.VISIBLE
            }
            else{
                binding.delete.visibility = View.GONE
            }
            binding.readMore.setOnClickListener {
                onItemClicked(article)
            }
            binding.delete.setOnClickListener {
                onDeleteClicked(article)
            }
        }
    }
}
