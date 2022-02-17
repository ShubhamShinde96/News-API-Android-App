package com.shubham.newsapiclientproject2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.shubham.newsapiclientproject2.databinding.ActivityMainBinding
import com.shubham.newsapiclientproject2.presentation.adapter.NewsAdapter
import com.shubham.newsapiclientproject2.presentation.viewmodel.NewsViewModel
import com.shubham.newsapiclientproject2.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NewsViewModel

    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory

    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvNews.setupWithNavController(navController)

        viewModel = ViewModelProvider(this, newsViewModelFactory)
            .get(NewsViewModel::class.java)

    }
}