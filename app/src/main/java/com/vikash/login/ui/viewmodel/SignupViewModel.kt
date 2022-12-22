package com.vikash.login.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.vikash.login.data.RemoteDataSource
import com.vikash.login.data.common.Result
import com.vikash.login.ui.model.LoginState
import com.vikash.login.data.db.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "SignupViewModel"
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }
    val signupComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    fun signup(userName: String, password: String, email: String) {
        viewModelScope.launch {
            when (val result = RemoteDataSource().signUp(email, password, userName)) {
                is Result.Success -> {
                    Log.d(TAG, "Sign UP : success")
                    LoginState.logIn(result.value.user)
                    signupComplete.value = true                }
                is Result.Failure -> {
                    Log.d(TAG, "Sign UP : failure")
                    error.value = result.value.message
                }
            }
        }
    }

}