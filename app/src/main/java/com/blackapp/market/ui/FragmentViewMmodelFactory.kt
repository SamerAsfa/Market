package com.blackapp.market.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blackapp.market.api.ApiRepository

class FragmentViewMmodelFactory(private val apiRepository:ApiRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return FragmentViewMmodel(apiRepository) as T
    }
}