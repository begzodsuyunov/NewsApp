package com.example.newsapp.utils

import androidx.fragment.app.Fragment
import com.example.newsapp.MainActivity

fun Fragment.hideProgress() {
    (requireActivity() as MainActivity).hideProgress()
}

fun Fragment.showProgress() {
    (requireActivity() as MainActivity).showProgress()
}