package com.blackapp.market.ui.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blackapp.market.api.ApiRepository

class ItemsFragmentViewModelFactory (private val apiRepository: ApiRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return ItemsFragmentViewModel(apiRepository) as T
    }
}