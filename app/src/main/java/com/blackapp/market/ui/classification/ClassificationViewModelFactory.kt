package com.blackapp.market.ui.classification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blackapp.market.api.ApiRepository

class ClassificationViewModelFactory (private val apiRepository: ApiRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClassificationViewModel(apiRepository) as T
    }

}