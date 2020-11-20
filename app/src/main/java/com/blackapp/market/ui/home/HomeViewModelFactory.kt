package com.blackapp.market.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blackapp.market.api.ApiRepository
import com.blackapp.market.ui.user_auth.UserAuthViewModel

class HomeViewModelFactory(private val apiRepository: ApiRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(apiRepository) as T
    }
}