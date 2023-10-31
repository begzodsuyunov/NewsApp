package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentBookmarksBinding
import com.example.newsapp.presenter.BookmarksViewModel
import com.example.newsapp.presenter.impl.BookmarksViewModelImpl
import com.example.newsapp.ui.adapter.BookmarkAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewBinding: FragmentBookmarksBinding by viewBinding(FragmentBookmarksBinding::bind)
    private val viewModel: BookmarksViewModel by viewModels<BookmarksViewModelImpl>()
    private val adapter: BookmarkAdapter by lazy { BookmarkAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.rvBookmark.adapter = adapter

        viewModel.bookmarksList.onEach {
            adapter.submitList(it)
            Log.d("LOGGI", "screen ${it} ")
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            val article = it.article
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToArticleFragment(
                    article!!
                )
            )
        }

    }
}