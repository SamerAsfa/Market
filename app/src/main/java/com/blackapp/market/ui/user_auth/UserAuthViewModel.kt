package com.blackapp.market.ui.user_auth

import android.text.TextUtils
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blackapp.market.api.ApiRepository
import com.blackapp.market.db.model.User

class UserAuthViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    var mAuthListener: AuthListener? = null
     var strLoginUserName: String? =null
     var strLoginUserPassword: String?= null
     var strSignUpUserName: String? =null
     var strSignUpUserPassword: String? =null
     var strSignUpUserConfirmPassword: String? =null
     var strSignUpPhone: String? =null
     var strSignUpEmail: String? =null

    public fun onLoginInButtonClick(view: View) {

//        mAuthListener?.onStarted()

     //   if (TextUtils.isEmpty(strLoginUserName) || TextUtils.isEmpty(strLoginUserPassword)) {
           // mAuthListener.onFailure(getString(R.string.field_required));
         //   return;
       // }
      //  LiveData<M_User> response = loginAuthentication(strLoginUserName, strLoginUserPassword, mToken);
        var user : LiveData<User> = MutableLiveData<User>()
        mAuthListener?.onLoginSuccess(user);
    }

    public fun onSignUpButtonClick(view:View) {
     /*   mAuthListener.onStarted();

        if (TextUtils.isEmpty(strSignUpUserName) || TextUtils.isEmpty(strSignUpUserPassword) || TextUtils.isEmpty(strSignUpUserConfirmPassword)
            || TextUtils.isEmpty(strSignUpPhone)|| TextUtils.isEmpty(strSignUpEmail)) {
            mAuthListener.onFailure(context.getString(R.string.field_required));
            return;
        }

        if (!TextUtils.equals(strSignUpUserPassword, strSignUpUserConfirmPassword)) {
            mAuthListener.onFailure(context.getString(R.string.password_and_confirm_password_not_match));
            return;
        }

        LiveData<String> response = signUp(strSignUpUserName, strSignUpUserPassword ,  strSignUpPhone , strSignUpEmail);
        mAuthListener.onSignUpSuccess(response);*/
    }


 /*   public void saveLoginUserData(M_User user) {
        clearLoginUserData();

        mTenderRepository.insertUser(new Users(user.getId(), user.getUsername(), user.getPassword(),
            user.getType(), user.getPhone(), user.getEmail(), user.getStart_subscription(), user.getEnd_subscription(), user.getLanguage()));
    }

    public void clearLoginUserData() {
        mTenderRepository.clearLoginUser_tb();
    }*/

}