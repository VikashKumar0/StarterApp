package com.vikash.login.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.vikash.login.databinding.FragmentSignupBinding
import com.vikash.login.ui.viewmodel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_signup.*

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel: SignupViewModel by viewModels<SignupViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupBtn.setOnClickListener { onSignup(it) }
        gotoLoginBtn.setOnClickListener { onGotoLogin(it) }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.signupComplete.observe(viewLifecycleOwner, Observer { isComplete ->
            Toast.makeText(context, "SignUp complete." , Toast.LENGTH_LONG ).show()
            val action = SignupFragmentDirections.actionGoToMain()
            Navigation.findNavController(binding.signupBtn).navigate(action)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(context, "Error: ${error.toString()}" , Toast.LENGTH_LONG ).show()
        })
    }

    private fun onSignup(v: View){
        var userName = binding.signupUsername.text.toString()
        var password = binding.signupPassword.text.toString()
        var email = binding.email.text.toString()

        if(userName.isNullOrEmpty() || password.isNullOrEmpty() || email.isNullOrEmpty()){
            Toast.makeText(context, "Please fill all the fields." , Toast.LENGTH_LONG ).show()
        }else{
            viewModel.signup(userName, password, email)
        }
    }

    private fun onGotoLogin(v: View) {
        val action = SignupFragmentDirections.actionGoToLogin()
        Navigation.findNavController(v).navigate(action)
    }
}
