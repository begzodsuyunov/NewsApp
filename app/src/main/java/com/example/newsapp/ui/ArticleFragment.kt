package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.data.local.room.NewsEntity
import com.example.newsapp.data.remote.model.Article
import com.example.newsapp.databinding.FragmentArticleBinding
import com.example.newsapp.presenter.ArticleViewModel
import com.example.newsapp.presenter.impl.ArticleViewModelImpl
import com.example.newsapp.utils.hideProgress
import com.example.newsapp.utils.showProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val viewBinding: FragmentArticleBinding by viewBinding(FragmentArticleBinding::bind)
    private var isFavourite: Boolean = false

    private val viewModel: ArticleViewModel by viewModels<ArticleViewModelImpl>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isBookmarked = false
        val args: Article? = requireArguments().getParcelable("article")


        viewBinding.webView.loadUrl(args!!.url!!)
        showProgress()

        viewBinding.webView.apply {
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    hideProgress()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            isBookmarked = viewModel.check(title = args.title!!)
            if (isBookmarked) {
                viewBinding.imgSave.setImageResource(R.drawable.favorite)
            } else {
                viewBinding.imgSave.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }

        viewBinding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }


        viewBinding.imgSave.setOnClickListener {
            isFavourite = !isFavourite
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.bookmarkArticle(NewsEntity(article = args))
                isBookmarked = viewModel.check(title = args.title!!)
                if (!isBookmarked) {
                    viewBinding.imgSave.setImageResource(R.drawable.favorite)
                } else {
                    viewBinding.imgSave.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        viewBinding.webView.destroy()
    }
}