package com.vikash.login.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikash.login.data.RemoteDataSource
import com.vikash.login.data.common.Result
import com.vikash.login.ui.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource) : ViewModel() {

    companion object{
        private const val TAG = "SignupViewModel"
    }

    val signupComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun signup(userName: String, password: String, email: String) {
        viewModelScope.launch {
            when (val result = remoteDataSource.signUp(email, password, userName)) {
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