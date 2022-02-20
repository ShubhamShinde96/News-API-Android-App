package com.shubham.newsapiclientproject2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.newsapiclientproject2.databinding.FragmentSavedNewsBinding
import com.shubham.newsapiclientproject2.presentation.adapter.NewsAdapter
import com.shubham.newsapiclientproject2.presentation.viewmodel.NewsViewModel


class SavedNewsFragment : Fragment() {

    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSavedNewsBinding.bind(view)

        newsViewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter

        initRecyclerView()

        newsViewModel.getSavedNews().observe(viewLifecycleOwner, Observer {

            Log.i("MY_TAG", "Saved News Size: ${it.size}")

            newsAdapter.differ.submitList(it)
        })

        newsAdapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }

            findNavController().navigate(
                R.id.action_savedNewsFragment_to_infoFragment,
                bundle
            )
        }


    }


    private fun initRecyclerView() {

        binding.rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}