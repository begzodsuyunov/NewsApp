package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.remote.model.Article
import com.example.newsapp.databinding.ItemAllNewsBinding

class AllNewsAdapter :
    PagingDataAdapter<Article, AllNewsAdapter.ArticleViewHolder>(PassengerComparator) {

    private var viewListener: ((Article) -> Unit)? = null

    fun setItemClickListener(block: ((Article) -> Unit)) {
        viewListener = block
    }


    override fun onBindViewHolder(holder: AllNewsAdapter.ArticleViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllNewsAdapter.ArticleViewHolder {
        return ArticleViewHolder(
            ItemAllNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ArticleViewHolder(private val binding: ItemAllNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        @SuppressLint("SetTextI18n")
//        fun bindPassenger(item: Article) = with(binding) {
////            Glide.with(binding.root)
////                .load(item.urlToImage)
////                .into(binding.imgItem)
//            titleItem.text = item.title
//            dateItem.text = item.author
//        }

        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            if (item != null) {
                binding.titleItem.text = item.title
                binding.dateItem.text = item.author
                Glide.with(binding.root)
                    .load(item.urlToImage)
                    .placeholder(R.drawable.placeholderimg)
                    .into(binding.imgItem)
            }

        }

        init {
            binding.root.setOnClickListener {
                viewListener?.invoke(getItem(absoluteAdapterPosition)!!)//shunga let yozsa boladi !! orniga nobodo crash bersa
            }
        }
    }

    object PassengerComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }


    }
}