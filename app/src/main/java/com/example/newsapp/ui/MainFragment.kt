package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentMainBinding
import com.example.newsapp.ui.adapter.MainPagerAdapter


class MainFragment : Fragment(R.layout.fragment_main) {
private val viewBinding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.pagerMain.adapter = MainPagerAdapter(requireActivity())
        viewBinding.pagerMain.isUserInputEnabled = false

        viewBinding.bottomNavigationView.setOnItemSelectedListener {
            viewBinding.pagerMain.setCurrentItem(
                when (it.itemId) {
                    R.id.home -> {
                        0
                    }
                    R.id.search -> {
                        1
                    }
                    else -> {
                        2
                    }
                }, true
            )
            true
        }

    }


}