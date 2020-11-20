package com.blackapp.market.ui.user_auth.signup

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.blackapp.market.R
import com.blackapp.market.api.ApiRepository
import com.blackapp.market.databinding.ActivitySignupBinding
import com.blackapp.market.db.model.User
import com.blackapp.market.ui.user_auth.AuthListener
import com.blackapp.market.ui.user_auth.UserAuthViewModel
import com.blackapp.market.ui.user_auth.UserAuthViewModelFactory
import com.blackapp.market.ui.user_auth.login.LoginActivity

class SignupActivity : AppCompatActivity(), AuthListener {

    private lateinit var signUpBinding: ActivitySignupBinding
    private lateinit var mViewModelFactory: UserAuthViewModelFactory
    private lateinit var mViewModel: UserAuthViewModel
    private lateinit var apiRepository: ApiRepository
    private var mUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_signup)
        apiRepository = ApiRepository()
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        mViewModelFactory = UserAuthViewModelFactory(apiRepository)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(UserAuthViewModel::class.java)
        signUpBinding.viewModel = mViewModel
        mViewModel.mAuthListener = this

        initViewViewer()
        uiListener()
    }

    private fun initViewViewer() {
        // video viewer
        mUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.video)
        signUpBinding.signUpVideoViewer.setVideoURI(mUri)
        signUpBinding.signUpVideoViewer.setOnPreparedListener { mp -> mp.isLooping = true }
        signUpBinding.signUpVideoViewer.start()
    }

    private fun uiListener() {

        // move to signup activity
        signUpBinding.txtSignUpActivityLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    override fun onStarted() {
        TODO("Not yet implemented")
    }

    override fun onLoginSuccess(response: LiveData<User>) {
        TODO("Not yet implemented")
    }

    override fun onSignUpSuccess(response: LiveData<String>) {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }
}