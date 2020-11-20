package com.blackapp.market.ui.user_auth

import androidx.lifecycle.LiveData
import com.blackapp.market.db.model.User

interface AuthListener {

    fun onStarted()

    fun onLoginSuccess(response:LiveData<User> )

    fun onSignUpSuccess(response:LiveData<String> )

    fun onFailure(message:String)
}