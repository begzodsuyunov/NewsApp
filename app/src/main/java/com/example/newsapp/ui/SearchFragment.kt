package com.example.newsapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSearchBinding
import com.example.newsapp.presenter.SearchViewModel
import com.example.newsapp.presenter.impl.SearchViewModelImpl
import com.example.newsapp.ui.adapter.AllNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewBinding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels<SearchViewModelImpl>()
    private val adapter = AllNewsAdapter()

    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvSearch.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchListFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
                Log.d("SSS", "$pagingData screenf")
            }
        }


        viewBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                handler.postDelayed({
                    if (query != null) {
                        viewModel.search(query)
                        Log.d("SSS", "$query on query submit")
                    }
                }, 200)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewLifecycleOwner.lifecycleScope.launch {
                    delay(500)
                    if (newText != null) {
                        viewModel.search(newText)
                        Log.d("SSS", "$newText")
                    }
                }


                return true
            }
        })

        adapter.setItemClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToArticleFragment(it))
        }
    }
}