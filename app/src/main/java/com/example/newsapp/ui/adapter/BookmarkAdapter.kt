package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.local.room.NewsEntity
import com.example.newsapp.databinding.ItemAllNewsBinding

class BookmarkAdapter : ListAdapter<NewsEntity, BookmarkAdapter.BookmarkViewHolder>(BookMarkCallBack) {

    private var viewListener: ((NewsEntity) -> Unit)? = null

    fun setItemClickListener(block: ((NewsEntity) -> Unit)) {
        viewListener = block
    }

    inner class BookmarkViewHolder(private val binding: ItemAllNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.titleItem.text = item.article!!.title
            Glide.with(binding.root)
                .load(item.article!!.urlToImage)
                .placeholder(R.drawable.placeholderimg)
                .into(binding.imgItem)
//            img.setImageURI(item.urlToImage)
        }

        init {
            binding.root.setOnClickListener {
                viewListener?.invoke(getItem(absoluteAdapterPosition)!!)//shunga let yozsa boladi !! orniga nobodo crash bersa
            }
        }
    }
    object BookMarkCallBack : DiffUtil.ItemCallback<NewsEntity>() {
        override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem.news_id == newItem.news_id
        }

        override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem.article!!.title == newItem.article!!.title &&
                    oldItem.article.content == newItem.article!!.content &&
                    oldItem.article.author == newItem.article!!.author &&
                    oldItem.article.url == newItem.article.url &&
                    oldItem.article.description == newItem.article.description &&
                    oldItem.article.publishedAt == newItem.article.publishedAt &&
                    oldItem.article.urlToImage == newItem.article.urlToImage
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            ItemAllNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind()
    }
}