package com.vikash.login.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vikash.login.ui.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
//    private val db by lazy{ UserDatabase(getApplication()).userDao()}
    val userDeleted = MutableLiveData<Boolean>()
    val signout = MutableLiveData<Boolean>()

    fun onSignout() {
        LoginState.logout()
        signout.value = true
    }

    fun onDeleteUser() {
        coroutineScope.launch {
            LoginState.user?.let {
//                db.deleteUser(user.id)
            }
            withContext(Dispatchers.Main){
                LoginState.logout()
                userDeleted.value = true
            }
        }
    }

}