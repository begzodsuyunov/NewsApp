package com.example.newsapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.ui.BookmarksFragment
import com.example.newsapp.ui.NewsFragment
import com.example.newsapp.ui.SearchFragment

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position){
        0 -> NewsFragment()
        1 -> SearchFragment()
        else -> BookmarksFragment()
    }
}