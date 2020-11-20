package com.blackapp.market.ui.slideshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blackapp.market.api.ApiRepository

class SlideshowViewModelFactory (private val apiRepository :ApiRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SlideshowViewModel(apiRepository) as T
    }

}