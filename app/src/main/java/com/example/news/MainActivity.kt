package com.example.news

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.adaptres.NewsAdapter
import com.example.news.adaptres.ViewPagerAdapter
import com.example.news.databinding.ActivityMainBinding
import com.example.news.network.NewsApiService
import com.example.news.network.RetrofitInstance
import com.example.news.repo.NewsRepository
import com.example.news.viewModel.NewsViewModel
import com.example.news.viewModel.NewsViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Home"
                1 -> "Saved"
                else -> null
            }
        }.attach()
    }
}