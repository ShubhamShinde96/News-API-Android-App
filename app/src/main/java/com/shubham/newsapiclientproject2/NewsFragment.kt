package com.shubham.newsapiclientproject2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shubham.newsapiclientproject2.data.model.Article
import com.shubham.newsapiclientproject2.data.util.Resource
import com.shubham.newsapiclientproject2.databinding.FragmentNewsBinding
import com.shubham.newsapiclientproject2.presentation.adapter.NewsAdapter
import com.shubham.newsapiclientproject2.presentation.viewmodel.NewsViewModel


class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding

    private lateinit var newsAdapter: NewsAdapter

    private var country = "us"
    private var page = 1

    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        newsViewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        binding = FragmentNewsBinding.bind(view)

        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {

        newsViewModel.getNewsHeadlines(country, page)

        newsViewModel.newsHeadlines.observe(viewLifecycleOwner) { response ->

//            Log.i("MY_TAG", "Response: ${response.data?.articles}")

            when (response) {

                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {

                        Log.i("MY_TAG", "Article List.Size(): ${response.data.articles.size}")

                        if (newsAdapter.differ.currentList.size > 0) {

                            var newList: List<Article> = newsAdapter.differ.currentList.toList()
                            var newList2: ArrayList<Article> = arrayListOf()
                            newList2.addAll(newList)
                            newList2.addAll(response.data.articles.toList())
                            newsAdapter.differ.submitList(newList2)

                        } else {

                            newsAdapter.differ.submitList(response.data.articles.toList())
                        }

                        if (it.totalResults / 20 == 0) {

                            pages = it.totalResults / 20

                        } else {

                            pages = it.totalResults / 20 + 1
                        }

                        isLastPage = page == pages
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(activity, "An error occurred: $response", Toast.LENGTH_LONG)
                        .show()
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun initRecyclerView() {

//        newsAdapter = NewsAdapter()

        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }

    private fun showProgressBar() {

        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
        binding.rvNews.visibility = View.GONE
    }

    private fun hideProgressBar() {

        isLoading = false
        binding.progressBar.visibility = View.GONE
        binding.rvNews.visibility = View.VISIBLE
    }


    private val onScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling

            if (shouldPaginate) {
                page++
                newsViewModel.getNewsHeadlines(country, page)
                isScrolling = false
            }
        }
    }


}