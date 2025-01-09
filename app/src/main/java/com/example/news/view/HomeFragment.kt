package com.example.news.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.news.R
import com.example.news.adaptres.NewsAdapter
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.db.AppDatabase
import com.example.news.network.NewsApiService
import com.example.news.network.RetrofitInstance
import com.example.news.repo.NewsRepository
import com.example.news.viewModel.NewsViewModel
import com.example.news.viewModel.NewsViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: NewsViewModel
    private val adapter = NewsAdapter(
        onItemClicked = { article ->
            val intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra("article", article)
            }
            startActivity(intent)
        },
        onDeleteClicked = { article ->
           //no required
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = RetrofitInstance.getInstance().create(NewsApiService::class.java)
        val repository = NewsRepository(apiService)
        val factory = NewsViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        binding.openSavedItemsButton.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
            viewPager?.currentItem = 1

        }
        viewModel.getNews("4ce4724df4144e1594d93cdf913f48fa").observe(viewLifecycleOwner){ response ->
            if (response!=null && response.isSuccessful&& response.body()!=null&& (response.body()!!.articles.isNotEmpty())){
                adapter.submitList(response.body()!!.articles, false)
                binding.errorLayout.visibility= View.GONE
            }
            else{
                binding.errorLayout.visibility= View.VISIBLE
            }
        }
    }
}