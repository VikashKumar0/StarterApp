package com.vikash.login.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.vikash.login.App
import com.vikash.login.data.RemoteDataSource
import com.vikash.login.ui.model.LoginState
import com.vikash.login.data.db.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.vikash.login.data.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource) : ViewModel() {
//    class LoginViewModelFactory(private val remoteDataSource: RemoteDataSource, val app: Application) : ViewModelProvider.Factory {
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return LoginViewModel(remoteDataSource, app) as T
//        }
//    }

//    private val coroutineScope = CoroutineScope(Dispatchers.IO)
//    private val db by lazy{ UserDatabase(getApplication()).userDao()}
    val loginComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            when (val result = remoteDataSource.login(username, password)) {
                is Result.Success -> {
                    LoginState.logIn(result.value.user)
                    loginComplete.value = true                }
                is Result.Failure -> {
                    error.value = "Login failed! "
                }
            }
        }
    }
}