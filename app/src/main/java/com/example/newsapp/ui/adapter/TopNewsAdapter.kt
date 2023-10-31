package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.newsapp.R
import com.example.newsapp.data.remote.model.Article
import com.example.newsapp.databinding.ItemTopNewsBinding

class TopNewsAdapter : ListAdapter<Article, TopNewsAdapter.TopNewsViewHolder>(CallBack) {

    private var viewListener: ((Article) -> Unit)? = null

    fun setItemClickListener(block: ((Article) -> Unit)) {
        viewListener = block
    }

    inner class TopNewsViewHolder(private val binding: ItemTopNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.itemTv.text = item.title
            Glide.with(binding.root)
                .load(item.urlToImage)
                .placeholder(R.drawable.placeholderimg)
                .into(binding.imageView)
//            img.setImageURI(item.urlToImage)
        }

        init {
            binding.root.setOnClickListener {
                viewListener?.invoke(getItem(absoluteAdapterPosition)!!)//shunga let yozsa boladi !! orniga nobodo crash bersa
            }
        }
    }

    object CallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.content == newItem.content &&
                    oldItem.author == newItem.author &&
                    oldItem.url == newItem.url &&
                    oldItem.description == newItem.description &&
                    oldItem.publishedAt == newItem.publishedAt &&
                    oldItem.urlToImage == newItem.urlToImage
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopNewsAdapter.TopNewsViewHolder {
        return  TopNewsViewHolder(
            ItemTopNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TopNewsAdapter.TopNewsViewHolder, position: Int) {
        holder.bind()
    }


}