package com.shubham.newsapiclientproject2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.shubham.newsapiclientproject2.databinding.FragmentInfoBinding
import com.shubham.newsapiclientproject2.presentation.viewmodel.NewsViewModel


class InfoFragment : Fragment() {


    private lateinit var binding: FragmentInfoBinding
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentInfoBinding.bind(view)

        val args: InfoFragmentArgs by navArgs()
        val article = args.selectedArticle

        newsViewModel = (activity as MainActivity).viewModel

        binding.wwNewsInfo.apply {

            webViewClient = WebViewClient()

            if(article.url != null && article.url != "") {
                loadUrl(article.url)
            }
        }

        binding.fabSave.setOnClickListener {

            val id = newsViewModel.saveArticle(article)
            Snackbar.make(view, "Saved Successfully, id: $id", Snackbar.LENGTH_LONG).show()
        }

    }

}