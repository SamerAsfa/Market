package com.blackapp.market.ui.user_auth.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.blackapp.market.R
import com.blackapp.market.api.ApiRepository
import com.blackapp.market.databinding.ActivityLoginBinding
import com.blackapp.market.db.model.User
import com.blackapp.market.ui.MainActivity
import com.blackapp.market.ui.user_auth.AuthListener
import com.blackapp.market.ui.user_auth.UserAuthViewModel
import com.blackapp.market.ui.user_auth.UserAuthViewModelFactory
import com.blackapp.market.ui.user_auth.signup.SignupActivity

class LoginActivity : AppCompatActivity(), AuthListener {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var mViewModelFactory: UserAuthViewModelFactory
    private lateinit var mViewModel: UserAuthViewModel
    private lateinit var apiRepository: ApiRepository
    private var mUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        apiRepository = ApiRepository()
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mViewModelFactory = UserAuthViewModelFactory(apiRepository)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(UserAuthViewModel::class.java)
        loginBinding.viewModel = mViewModel
        mViewModel.mAuthListener = this

        initViewViewer()
        uiListener()
    }

    private fun uiListener() {

        // move to signup activity
        loginBinding.txtLoginActivitySignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()

        }

    }

    private fun initViewViewer() {
        // video viewer
        mUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.video)
        loginBinding.loginVideoViewer.setVideoURI(mUri)
        loginBinding.loginVideoViewer.setOnPreparedListener { mp -> mp.isLooping = true }
        loginBinding.loginVideoViewer.start()
    }


    override fun onStarted() {
        TODO("Not yet implemented")
    }

    override fun onLoginSuccess(response: LiveData<User>) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onSignUpSuccess(response: LiveData<String>) {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }
}