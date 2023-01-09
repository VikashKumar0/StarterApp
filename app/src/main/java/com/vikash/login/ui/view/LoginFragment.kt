package com.vikash.login.ui.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.vikash.login.databinding.FragmentLoginBinding
import com.vikash.login.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  super.onCreateView(inflater, container, savedInstanceState)

    }

    private val viewModel: LoginViewModel by viewModels<LoginViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBtn.setOnClickListener { onLogin(it) }
        gotoSignupBtn.setOnClickListener { onGotoSignup(it) }
        
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginComplete.observe(viewLifecycleOwner, Observer { isComplete ->
            Toast.makeText(context, "login Complete.", Toast.LENGTH_LONG).show()
            val action = LoginFragmentDirections.actionGoToMain()
            Navigation.findNavController(binding.loginUsername).navigate(action)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(context, "Error: ${error.toString()}", Toast.LENGTH_LONG).show()
        })
    }

    private fun onLogin(v: View) {
        var username = binding.loginUsername.text.toString()
        var password = binding.loginPassword.text.toString()
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            Toast.makeText(context, "Please fill all the fields.", Toast.LENGTH_LONG).show()
        } else {
            viewModel.login(username, password)
        }
    }

    private fun onGotoSignup(v: View) {
        val action = LoginFragmentDirections.actionGoToSignup()
        Navigation.findNavController(v).navigate(action)
    }
}
