package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.presenter.NewsViewModel
import com.example.newsapp.presenter.impl.NewsViewModelImpl
import com.example.newsapp.ui.adapter.AllNewsAdapter
import com.example.newsapp.ui.adapter.TopNewsAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private val viewBinding: FragmentNewsBinding by viewBinding(FragmentNewsBinding::bind)
    private val viewModel: NewsViewModel by viewModels<NewsViewModelImpl>()
    private val topAdapter = TopNewsAdapter()
    private val allAdapter = AllNewsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvTopNews.adapter = topAdapter
        viewBinding.rvRecentNews.adapter = allAdapter


        viewModel.latestNewsList.onEach {
            allAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.topHeadlinesList.onEach {
            topAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.topHeadlines("business")

        viewBinding.priorityChipGroup.setOnCheckedStateChangeListener { chipGroup, i ->
            changePriority(chipGroup, i)
        }

        allAdapter.setItemClickListener {
            Log.d("NNNNN", "${it.title}")
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToArticleFragment(it))
        }

        topAdapter.setItemClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToArticleFragment(it))
        }

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(viewBinding.rvTopNews)

        val snapHelper2: SnapHelper = LinearSnapHelper()
        snapHelper2.attachToRecyclerView(viewBinding.rvRecentNews)
    }

    private fun changePriority(chipGroup: ChipGroup, i: List<Int>) {
        val id = i[0]
        val chip = chipGroup.findViewById(id) as Chip
        when (chip.text) {
            "technology" -> viewModel.topHeadlines("technology")
            "entertainment" -> viewModel.topHeadlines("entertainment")
            "health" -> viewModel.topHeadlines("health")
            "science" -> viewModel.topHeadlines("science")
            "sport" -> viewModel.topHeadlines("sport")
            else -> viewModel.topHeadlines("business")
        }
    }
}


