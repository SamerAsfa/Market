package com.blackapp.market.ui.user_auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blackapp.market.api.ApiRepository

class UserAuthViewModelFactory(private val apiRepository: ApiRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserAuthViewModel(apiRepository) as T
    }
}