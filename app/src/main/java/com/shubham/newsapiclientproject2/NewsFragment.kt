package com.shubham.newsapiclientproject2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding = FragmentNewsBinding.bind(view)

        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {

        newsViewModel.getNewsHeadlines(country, page)

        newsViewModel.newsHeadlines.observe(viewLifecycleOwner) { response ->

//            Log.i("MY_TAG", "Response: ${response.data?.articles}")

            when(response) {

                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {

                        Log.i("MY_TAG", "Article List.Size(): ${response.data.articles.size}")
                        newsAdapter.differ.submitList(response.data.articles.toList())
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(activity, "An error occurred: $response", Toast.LENGTH_LONG).show()
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun initRecyclerView() {

        newsAdapter = NewsAdapter()

        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar() {

        binding.progressBar.visibility = View.VISIBLE
        binding.rvNews.visibility = View.GONE
    }

    private fun hideProgressBar() {

        binding.progressBar.visibility = View.GONE
        binding.rvNews.visibility = View.VISIBLE
    }


}